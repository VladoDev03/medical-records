package com.medical_records.prescription_service.data.repository;

import com.medical_records.prescription_service.data.document.Medicine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MedicineRepository extends ReactiveMongoRepository<Medicine, Long> {
}
