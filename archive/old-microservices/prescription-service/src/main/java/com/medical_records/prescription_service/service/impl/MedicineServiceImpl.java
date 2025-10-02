package com.medical_records.prescription_service.service.impl;

import com.medical_records.prescription_service.data.document.Medicine;
import com.medical_records.prescription_service.data.repository.MedicineRepository;
import com.medical_records.prescription_service.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Flux<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
}
