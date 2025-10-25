package org.example.patientservice.service.contracts;

import org.example.patientservice.dto.CreatePatientDto;
import org.example.patientservice.dto.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(long id);
    PatientDto createPatient(CreatePatientDto patient);
}
