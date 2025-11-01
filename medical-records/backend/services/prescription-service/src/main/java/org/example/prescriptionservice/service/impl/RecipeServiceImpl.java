package org.example.prescriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.data.document.Medicine;
import org.example.prescriptionservice.data.document.MedicineItem;
import org.example.prescriptionservice.data.document.Recipe;
import org.example.prescriptionservice.data.repo.RecipeRepository;
import org.example.prescriptionservice.dto.medicine.CreateMedicineItemDto;
import org.example.prescriptionservice.dto.medicine.MedicineItemDto;
import org.example.prescriptionservice.dto.recipe.CreateRecipeDto;
import org.example.prescriptionservice.dto.recipe.RecipeDto;
import org.example.prescriptionservice.exception.DocumentNotFoundException;
import org.example.prescriptionservice.service.contracts.MedicineService;
import org.example.prescriptionservice.service.contracts.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ModelMapper mapper;
    private final MedicineService medicineService;

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
        List<String> ids = recipe
                .getMedicines()
                .stream()
                .map(CreateMedicineItemDto::getMedicineId)
                .toList();

        Recipe newRecipe = mapper.map(recipe, Recipe.class);

        return medicineService
                .getMedicinesByIds(ids)
                .flatMap(medicineDto -> {
                    CreateMedicineItemDto dto = recipe
                            .getMedicines()
                            .stream()
                            .filter(medicineDtoItem -> medicineDtoItem
                                    .getMedicineId()
                                    .equals(medicineDto.getId())
                            )
                            .findFirst()
                            .orElseThrow();

                    MedicineItem item = mapper.map(dto, MedicineItem.class);
                    item.setMedicine(mapper.map(medicineDto, Medicine.class));

                    return Mono.just(item);
                })
                .collectList()
                .flatMap(items -> {
                    newRecipe.setMedicines(items);
                    return recipeRepository.save(newRecipe);
                })
                .map(savedRecipe -> mapper.map(savedRecipe, RecipeDto.class));
    }
}
