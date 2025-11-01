package org.example.prescriptionservice.data.repo;

import org.example.prescriptionservice.data.document.Medicine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MedicineRepository extends ReactiveMongoRepository<Medicine, String> { }
