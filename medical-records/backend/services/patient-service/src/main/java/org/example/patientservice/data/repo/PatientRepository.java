package org.example.patientservice.data.repo;

import org.example.patientservice.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> { }
