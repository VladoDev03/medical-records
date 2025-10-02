package com.medical_records.doctor_service.controller;

import com.medical_records.doctor_service.data.entity.Doctor;
import com.medical_records.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public Flux<Doctor> getDoctors() {
        return doctorService.getAllDoctors();
    }
}
