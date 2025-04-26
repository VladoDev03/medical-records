package com.medical_records.doctor_service.service;

import com.medical_records.doctor_service.data.entity.Doctor;
import reactor.core.publisher.Flux;

public interface DoctorService {
    Flux<Doctor> getAllDoctors();
}
