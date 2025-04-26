package com.medical_records.doctor_service.data.repository;

import com.medical_records.doctor_service.data.entity.Doctor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DoctorRepository extends R2dbcRepository<Doctor, Long> {
}
