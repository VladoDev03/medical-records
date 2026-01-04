package org.example.visitservice.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.visitservice.client.DoctorClient;
import org.example.visitservice.client.PatientClient;
import org.example.visitservice.config.ModelMapperConfig;
import org.example.visitservice.data.entity.Diagnose;
import org.example.visitservice.data.entity.Visit;
import org.example.visitservice.data.repo.VisitRepository;
import org.example.visitservice.dto.doctor.DoctorDto;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.DoctorVisitCountDto;
import org.example.visitservice.dto.visit.UpdateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
import org.example.visitservice.exception.EntityNotFoundException;
import org.example.visitservice.service.contracts.DiagnoseService;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapperConfig mapperConfig;
    private final DiagnoseService diagnoseService;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    @Override
    public List<VisitDto> getVisits() {
        return mapperConfig.mapList(visitRepository.findAllWithDiagnoses(), VisitDto.class);
    }

    @Override
    public List<VisitDto> getVisitsByPatientId(long patientId) {
        return mapperConfig.mapList(visitRepository.findAllByPatientId(patientId), VisitDto.class);
    }

    @Override
    public VisitDto getVisitById(long id) {
        Visit visit = findVisitOrThrow(id);
        return mapperConfig.getModelMapper().map(visit, VisitDto.class);
    }

    @Override
    public VisitDto createVisit(CreateVisitDto visit) {
        try {
            doctorClient.getDoctorById(visit.getDoctorId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Doctor not found with id: " + visit.getDoctorId());
        }

        try {
            patientClient.getPatientById(visit.getPatientId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Patient not found with id: " + visit.getPatientId());
        }

        Visit newVisit = mapperConfig.getModelMapper().map(visit, Visit.class);
        Visit savedVisit = visitRepository.save(newVisit);

        return mapperConfig.getModelMapper().map(savedVisit, VisitDto.class);
    }

    @Override
    public VisitDto updateVisit(long id, UpdateVisitDto visitDto) {
        Visit visit = findVisitOrThrow(id);

        Set<Diagnose> diagnoses = diagnoseService
                .getDiagnosesByIds(visitDto.getDiagnosesIds())
                .stream()
                .map(diagnose -> mapperConfig.getModelMapper().map(diagnose, Diagnose.class))
                .collect(Collectors.toSet());

        visit.setDiagnoses(diagnoses);
        VisitDto updatedVisit = mapperConfig.getModelMapper().map(visitRepository.save(visit), VisitDto.class);

        updatedVisit.setDiagnosesNames(diagnoses
                .stream()
                .map(Diagnose::getName)
                .toList()
        );

        return updatedVisit;
    }

    @Override
    public List<DoctorVisitCountDto> getVisitCountsForAllDoctors() {
        List<DoctorDto> allDoctors = doctorClient.getAllDoctors();
        List<DoctorVisitCountDto> counts = mapperConfig.mapList(visitRepository.countVisitsGroupedByDoctor(), DoctorVisitCountDto.class);

        Map<Long, Long> visitMap = counts
                .stream()
                .collect(Collectors.toMap(DoctorVisitCountDto::getDoctorId, DoctorVisitCountDto::getVisitCount));

        return allDoctors
                .stream()
                .map(d -> new DoctorVisitCountDto(d.getId(), visitMap.getOrDefault(d.getId(), 0L)))
                .toList();
    }

    private Visit findVisitOrThrow(long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with id: " + id));
    }
}
