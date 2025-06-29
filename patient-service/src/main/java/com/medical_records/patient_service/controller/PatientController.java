package com.medical_records.patient_service.controller;

import com.medical_records.patient_service.data.entity.Patient;
import com.medical_records.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient registerPatient() {
        Patient patient = new Patient(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                LocalDate.now()
        );

        return patientService.createPatient(patient);
    }
}
