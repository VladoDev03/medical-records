package org.example.prescriptionservice.service.contracts;

import org.example.prescriptionservice.dto.medicine.CreateMedicineDto;
import org.example.prescriptionservice.dto.medicine.MedicineDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MedicineService {
    Flux<MedicineDto> getMedicines();
    Mono<MedicineDto> getMedicineById(String id);
    Mono<MedicineDto> createMedicine(CreateMedicineDto medicine);
}
