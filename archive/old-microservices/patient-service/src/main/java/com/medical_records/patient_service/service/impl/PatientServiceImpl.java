package com.medical_records.patient_service.service.impl;

import com.medical_records.patient_service.controller.DoctorServiceClient;
import com.medical_records.patient_service.data.entity.Patient;
import com.medical_records.patient_service.data.repository.PatientRepository;
import com.medical_records.patient_service.service.PatientService;
import com.medical_records.patient_service.viewmodel.DoctorModel;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private final DoctorServiceClient doctorServiceClient;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<DoctorModel> getDoctors() {
        List<DoctorModel> result = new ArrayList<>();

        try {
            result = this.doctorServiceClient.getDoctors();
        }
        catch (FeignException feignException) {
            feignException.printStackTrace();
        }

        return result;
    }
}
