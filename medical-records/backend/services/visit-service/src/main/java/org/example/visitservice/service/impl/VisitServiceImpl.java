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
import org.example.visitservice.dto.patient.PatientDto;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.DoctorVisitCountDto;
import org.example.visitservice.dto.visit.UpdateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
import org.example.visitservice.exception.EntityNotFoundException;
import org.example.visitservice.exception.ExternalServiceUnavailableException;
import org.example.visitservice.service.contracts.DiagnoseService;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        try {
            patientClient.getPatientById(patientId);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Patient not found with id: " + patientId);
        } catch (FeignException | ResourceAccessException e) {
            throw new ExternalServiceUnavailableException("Patient service is currently unavailable.");
        }

        return mapperConfig.mapList(visitRepository.findAllByPatientId(patientId), VisitDto.class);
    }

    @Override
    public VisitDto getVisitById(long id) {
        Visit visit = findVisitOrThrow(id);
        return mapperConfig.getModelMapper().map(visit, VisitDto.class);
    }

    @Override
    public VisitDto createVisit(CreateVisitDto visit) {
        PatientDto patient;
        DoctorDto doctor;

        try {
            doctor = doctorClient.getDoctorById(visit.getDoctorId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Doctor not found with id: " + visit.getDoctorId());
        } catch (FeignException | ResourceAccessException e) {
            throw new ExternalServiceUnavailableException("Doctor service is currently unavailable.");
        }

        try {
            patient = patientClient.getPatientById(visit.getPatientId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Patient not found with id: " + visit.getPatientId());
        } catch (FeignException | ResourceAccessException e) {
            throw new ExternalServiceUnavailableException("Patient service is currently unavailable.");
        }

        if (Objects.equals(patient.getKeycloakId(), doctor.getKeycloakId())) {
            throw new IllegalArgumentException("Patient cannot visit himself.");
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
        List<DoctorDto> allDoctors;

        try {
            allDoctors = doctorClient.getAllDoctors();
        } catch (FeignException | ResourceAccessException e) {
            throw new ExternalServiceUnavailableException("Doctor service is currently unavailable.");
        }

        List<DoctorVisitCountDto> counts = visitRepository.countVisitsGroupedByDoctor();

        Map<Long, Long> visitMap = counts
                .stream()
                .collect(Collectors.toMap(DoctorVisitCountDto::getDoctorId, DoctorVisitCountDto::getVisitCount));

        return allDoctors
                .stream()
                .map(d -> new DoctorVisitCountDto(d.getId(), visitMap.getOrDefault(d.getId(), 0L)))
                .toList();
    }

    @Override
    public List<VisitDto> getVisitsInPeriod(LocalDate startDate, LocalDate endDate) {
        return mapperConfig.mapList(
                visitRepository.findAllByVisitDateBetween(startDate, endDate),
                VisitDto.class
        );
    }

    @Override
    public List<VisitDto> getVisitsForDoctorInPeriod(long doctorId, LocalDate startDate, LocalDate endDate) {
        try {
            doctorClient.getDoctorById(doctorId);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Doctor not found with id: " + doctorId);
        } catch (FeignException | ResourceAccessException e) {
            throw new ExternalServiceUnavailableException("Doctor service is currently unavailable.");
        }

        return mapperConfig.mapList(
                visitRepository.findAllByDoctorIdAndVisitDateBetween(doctorId, startDate, endDate),
                VisitDto.class
        );
    }

    @Override
    public void deleteVisit(long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Visit not found with id: " + id)
                );

        if (!visit.getVisitDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot delete a visit that has already passed or is today.");
        }

        visitRepository.delete(visit);
    }

    private Visit findVisitOrThrow(long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with id: " + id));
    }
}
