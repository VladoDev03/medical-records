package com.medical_records.doctor_service.service.impl;

import com.medical_records.doctor_service.data.entity.Doctor;
import com.medical_records.doctor_service.data.repository.DoctorRepository;
import com.medical_records.doctor_service.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
