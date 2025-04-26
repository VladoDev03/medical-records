package com.medical_records.patient_service.data.repository;

import com.medical_records.patient_service.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
