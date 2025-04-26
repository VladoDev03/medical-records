package com.medical_records.patient_service.service.impl;

import com.medical_records.patient_service.data.entity.Patient;
import com.medical_records.patient_service.data.repository.PatientRepository;
import com.medical_records.patient_service.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
