package com.medical_records.patient_service.controller;

import com.medical_records.patient_service.viewmodel.DoctorModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "doctor-service")
@Component
public interface DoctorServiceClient {
    @GetMapping("/api/doctors")
    List<DoctorModel> getDoctors();

    @GetMapping("/doctors/{id}")
    DoctorModel getDoctorById(@PathVariable("id") long id);
}
