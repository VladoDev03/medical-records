package com.medical_records.patient_service.service;

import com.medical_records.patient_service.data.entity.Patient;
import com.medical_records.patient_service.viewmodel.DoctorModel;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient createPatient(Patient patient);
    List<DoctorModel> getDoctors();
}
