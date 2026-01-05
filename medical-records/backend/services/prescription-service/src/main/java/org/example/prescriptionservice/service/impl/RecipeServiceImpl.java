package org.example.prescriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.client.DoctorClient;
import org.example.prescriptionservice.client.PatientClient;
import org.example.prescriptionservice.client.VisitClient;
import org.example.prescriptionservice.data.document.Medicine;
import org.example.prescriptionservice.data.document.MedicineItem;
import org.example.prescriptionservice.data.document.Recipe;
import org.example.prescriptionservice.data.repo.RecipeRepository;
import org.example.prescriptionservice.dto.doctor.DoctorDto;
import org.example.prescriptionservice.dto.medicine.CreateMedicineItemDto;
import org.example.prescriptionservice.dto.medicine.MedicineDto;
import org.example.prescriptionservice.dto.medicine.MedicineItemDto;
import org.example.prescriptionservice.dto.patient.PatientDto;
import org.example.prescriptionservice.dto.recipe.CreateRecipeDto;
import org.example.prescriptionservice.dto.recipe.RecipeDto;
import org.example.prescriptionservice.dto.visit.VisitDto;
import org.example.prescriptionservice.exception.DocumentNotFoundException;
import org.example.prescriptionservice.exception.ExternalServiceUnavailableException;
import org.example.prescriptionservice.service.contracts.MedicineService;
import org.example.prescriptionservice.service.contracts.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ModelMapper mapper;
    private final MedicineService medicineService;
    private final PatientClient patientClient;
    private final DoctorClient doctorClient;
    private final VisitClient visitClient;

    @Override
    public Flux<RecipeDto> getRecipes() {
        return recipeRepository
                .findAll()
                .map(recipe -> {
                    RecipeDto recipeDto = mapper.map(recipe, RecipeDto.class);

                    List<MedicineItemDto> medicineItems = recipe
                            .getMedicines()
                            .stream()
                            .map(medicineItem -> mapper.map(medicineItem, MedicineItemDto.class))
                            .toList();

                    recipeDto.setMedicines(medicineItems);

                    return recipeDto;
                });
    }

    @Override
    public Mono<RecipeDto> getRecipeById(String id) {
        return recipeRepository.findById(id)
                .switchIfEmpty(Mono.error(new DocumentNotFoundException(
                        "Recipe not found with id: " + id,
                        HttpStatus.NOT_FOUND.value()
                )))
                .map(recipe -> mapper.map(recipe, RecipeDto.class));
    }

    @Override
    public Mono<RecipeDto> createRecipe(CreateRecipeDto recipe) {
        validateDuplicateMedicines(recipe);

        Mono<DoctorDto> doctorMono = getDoctor(recipe.getDoctorId());
        Mono<PatientDto> patientMono = getPatient(recipe.getPatientId());
        Mono<VisitDto> visitMono = getVisit(recipe.getVisitId());

        return Mono.zip(doctorMono, patientMono, visitMono)
                .flatMap(tuple -> buildAndSaveRecipe(recipe))
                .onErrorMap(e -> new RuntimeException("Failed to create recipe", e));
    }

    private void validateDuplicateMedicines(CreateRecipeDto recipe) {
        List<String> medicineIds = recipe.getMedicines()
                .stream()
                .map(CreateMedicineItemDto::getMedicineId)
                .toList();

        long uniqueCount = medicineIds
                .stream()
                .distinct()
                .count();

        if (uniqueCount < medicineIds.size()) {
            throw new IllegalArgumentException("Duplicate medicines are not allowed in a recipe.");
        }
    }

    private Mono<DoctorDto> getDoctor(long doctorId) {
        return doctorClient.getDoctorById(doctorId)
                .onErrorMap(WebClientResponseException.NotFound.class,
                        e -> new IllegalArgumentException("Doctor not found with id: " + doctorId))
                .onErrorMap(WebClientRequestException.class,
                        e -> new ExternalServiceUnavailableException("Doctor service is currently unavailable"))
                .onErrorMap(e -> new ExternalServiceUnavailableException("Doctor service is currently unavailable"));
    }

    private Mono<PatientDto> getPatient(long patientId) {
        return patientClient.getPatientById(patientId)
                .onErrorMap(WebClientResponseException.NotFound.class,
                        e -> new IllegalArgumentException("Patient not found with id: " + patientId))
                .onErrorMap(WebClientRequestException.class,
                        e -> new ExternalServiceUnavailableException("Patient service is currently unavailable"))
                .onErrorMap(e -> new ExternalServiceUnavailableException("Patient service is currently unavailable"));
    }

    private Mono<VisitDto> getVisit(long visitId) {
        return visitClient.getVisitById(visitId)
                .onErrorMap(WebClientResponseException.NotFound.class,
                        e -> new IllegalArgumentException("Visit not found with id: " + visitId))
                .onErrorMap(WebClientRequestException.class,
                        e -> new ExternalServiceUnavailableException("Visit service is currently unavailable"))
                .onErrorMap(e -> new ExternalServiceUnavailableException("Visit service is currently unavailable"));
    }

    private Mono<RecipeDto> buildAndSaveRecipe(CreateRecipeDto recipe) {
        Recipe newRecipe = mapper.map(recipe, Recipe.class);

        List<String> medicineIds = recipe.getMedicines()
                .stream()
                .map(CreateMedicineItemDto::getMedicineId)
                .toList();

        return medicineService.getMedicinesByIds(medicineIds)
                .flatMap(medicineDto -> mapMedicineItem(recipe, medicineDto))
                .collectList()
                .flatMap(items -> {
                    newRecipe.setMedicines(items);
                    return recipeRepository.save(newRecipe);
                })
                .map(savedRecipe -> mapper.map(savedRecipe, RecipeDto.class));
    }

    private Mono<MedicineItem> mapMedicineItem(CreateRecipeDto recipe, MedicineDto medicineDto) {
        CreateMedicineItemDto dto = recipe.getMedicines().stream()
                .filter(m -> m.getMedicineId().equals(medicineDto.getId()))
                .findFirst()
                .orElseThrow();

        MedicineItem item = mapper.map(dto, MedicineItem.class);
        item.setMedicine(mapper.map(medicineDto, Medicine.class));
        return Mono.just(item);
    }
}
