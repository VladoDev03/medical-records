package org.example.prescriptionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.data.document.Medicine;
import org.example.prescriptionservice.data.repo.MedicineRepository;
import org.example.prescriptionservice.dto.medicine.CreateMedicineDto;
import org.example.prescriptionservice.dto.medicine.MedicineDto;
import org.example.prescriptionservice.exception.DocumentNotFoundException;
import org.example.prescriptionservice.service.contracts.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return medicineRepository.findById(id)
                .switchIfEmpty(Mono.error(new DocumentNotFoundException(
                        "Medicine not found with id: " + id,
                        HttpStatus.NOT_FOUND.value()
                )))
                .map(medicine -> mapper.map(medicine, MedicineDto.class));
    }

    @Override
    public Mono<MedicineDto> createMedicine(CreateMedicineDto dto) {
        Medicine newMedicine = mapper.map(dto, Medicine.class);

        return medicineRepository.save(newMedicine)
                .map(savedMedicine -> mapper.map(savedMedicine, MedicineDto.class));
    }
}
