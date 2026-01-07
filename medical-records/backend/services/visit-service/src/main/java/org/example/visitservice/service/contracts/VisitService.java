package org.example.visitservice.service.contracts;

import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.DoctorVisitCountDto;
import org.example.visitservice.dto.visit.UpdateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {
    List<VisitDto> getVisits();
    List<VisitDto> getVisitsByPatientId(long patientId);
    VisitDto getVisitById(long id);
    VisitDto createVisit(CreateVisitDto visitDto);
    VisitDto updateVisit(long id, UpdateVisitDto visitDto);
    List<DoctorVisitCountDto> getVisitCountsForAllDoctors();
    List<VisitDto> getVisitsInPeriod(LocalDate startDate, LocalDate endDate);
    List<VisitDto> getVisitsForDoctorInPeriod(long doctorId, LocalDate startDate, LocalDate endDate);
    void deleteVisit(long id);
}
