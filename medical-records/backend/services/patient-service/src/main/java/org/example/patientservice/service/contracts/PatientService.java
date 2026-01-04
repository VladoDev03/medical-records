package org.example.patientservice.service.contracts;

import org.example.patientservice.dto.patient.CreatePatientDto;
import org.example.patientservice.dto.patient.GpPatientCountDto;
import org.example.patientservice.dto.patient.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(long id);
    PatientDto createPatient(CreatePatientDto patient);
    List<GpPatientCountDto> getPatientCountsForAllGp();
    List<PatientDto> getPatientsByGpId(long gpId);
}
