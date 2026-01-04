package org.example.patientservice.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.client.DoctorClient;
import org.example.patientservice.config.ModelMapperConfig;
import org.example.patientservice.data.entity.Patient;
import org.example.patientservice.data.repo.PatientRepository;
import org.example.patientservice.dto.patient.CreatePatientDto;
import org.example.patientservice.dto.patient.GpPatientCountDto;
import org.example.patientservice.dto.patient.PatientDto;
import org.example.patientservice.exception.EntityNotFoundException;
import org.example.patientservice.service.contracts.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapperConfig mapperConfig;
    private final DoctorClient doctorClient;

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
        try {
            doctorClient.getDoctorById(patient.getGpId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Doctor not found with id: " + patient.getGpId());
        }

        Patient newPatient = mapperConfig.getModelMapper().map(patient, Patient.class);
        Patient savedPatient = patientRepository.save(newPatient);

        return mapperConfig.getModelMapper().map(savedPatient, PatientDto.class);
    }

    @Override
    public List<PatientDto> getPatientsByGpId(long gpId) {
        return mapperConfig.mapList(patientRepository.findAllByGpId(gpId), PatientDto.class);
    }

    @Override
    public List<GpPatientCountDto> getPatientCountsForAllGp() {
        return patientRepository.countPatientsGroupedByGp();
    }
}
