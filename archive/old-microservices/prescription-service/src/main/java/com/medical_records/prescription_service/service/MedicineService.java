package com.medical_records.prescription_service.service;

import com.medical_records.prescription_service.data.document.Medicine;
import reactor.core.publisher.Flux;

public interface MedicineService {
    Flux<Medicine> getAllMedicines();
}
