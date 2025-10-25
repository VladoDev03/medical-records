package org.example.patientservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.patientservice.config.ModelMapperConfig;
import org.example.patientservice.data.entity.Patient;
import org.example.patientservice.data.repo.PatientRepository;
import org.example.patientservice.dto.CreatePatientDto;
import org.example.patientservice.dto.PatientDto;
import org.example.patientservice.exception.EntityNotFoundException;
import org.example.patientservice.service.contracts.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public List<PatientDto> getAllPatients() {
        return mapperConfig.mapList(patientRepository.findAll(), PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));

        return mapperConfig.getModelMapper().map(patient, PatientDto.class);
    }

    @Override
    public PatientDto createPatient(CreatePatientDto patient) {
        Patient newPatient = mapperConfig.getModelMapper().map(patient, Patient.class);
        Patient savedPatient = patientRepository.save(newPatient);

        return mapperConfig.getModelMapper().map(savedPatient, PatientDto.class);
    }
}
