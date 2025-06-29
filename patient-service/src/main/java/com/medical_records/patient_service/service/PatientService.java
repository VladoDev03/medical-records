package com.medical_records.patient_service.service;

import com.medical_records.patient_service.data.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient createPatient(Patient patient);
}
