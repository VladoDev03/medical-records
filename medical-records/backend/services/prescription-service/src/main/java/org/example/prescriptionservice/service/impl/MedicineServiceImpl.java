package org.example.prescriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.data.document.Medicine;
import org.example.prescriptionservice.data.repo.MedicineRepository;
import org.example.prescriptionservice.dto.medicine.CreateMedicineDto;
import org.example.prescriptionservice.dto.medicine.MedicineDto;
import org.example.prescriptionservice.dto.medicine.UpdateMedicineDto;
import org.example.prescriptionservice.exception.DocumentNotFoundException;
import org.example.prescriptionservice.service.contracts.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final ModelMapper mapper;

    @Override
    public Flux<MedicineDto> getMedicines() {
        return medicineRepository.findAll()
                .map(medicine -> mapper.map(medicine, MedicineDto.class));
    }

    @Override
    public Mono<MedicineDto> getMedicineById(String id) {
        return findMedicineOrThrow(id)
                .map(medicine -> mapper.map(medicine, MedicineDto.class));
    }

    @Override
    public Flux<MedicineDto> getMedicinesByIds(List<String> ids) {
        return medicineRepository.findAllById(ids)
                .collectList()
                .flatMapMany(medicines -> {
                    if (medicines.size() != ids.size()) {
                        List<String> foundIds = medicines
                                .stream()
                                .map(Medicine::getId)
                                .toList();

                        ids.stream()
                                .filter(id -> !foundIds.contains(id))
                                .forEach(id -> {
                                    throw new DocumentNotFoundException(
                                            "Medicine not found with id: " + id,
                                            HttpStatus.NOT_FOUND.value()
                                    );
                                });
                    }

                    return Flux.fromIterable(
                            medicines.stream()
                                    .map(medicine -> mapper.map(medicine, MedicineDto.class))
                                    .toList()
                    );
                });
    }

    @Override
    public Mono<MedicineDto> createMedicine(CreateMedicineDto medicine) {
        Medicine newMedicine = mapper.map(medicine, Medicine.class);

        return medicineRepository.save(newMedicine)
                .map(savedMedicine -> mapper.map(savedMedicine, MedicineDto.class));
    }

    @Override
    public Mono<MedicineDto> updateMedicine(String id, UpdateMedicineDto medicineDto) {
        return findMedicineOrThrow(id)
                .flatMap(existingMedicine -> {

                    if (medicineDto.getName() != null) {
                        existingMedicine.setName(medicineDto.getName());
                    }

                    if (medicineDto.getManufacturer() != null) {
                        existingMedicine.setManufacturer(medicineDto.getManufacturer());
                    }

                    return medicineRepository.save(existingMedicine);
                })
                .map(savedMedicine -> mapper.map(savedMedicine, MedicineDto.class));
    }


    private Mono<Medicine> findMedicineOrThrow(String id) {
        return medicineRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new DocumentNotFoundException(
                                "Medicine not found with id: " + id,
                                HttpStatus.NOT_FOUND.value()
                        )
                ));
    }
}
