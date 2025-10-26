package org.example.visitservice.service.contracts;

import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.UpdateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;

import java.util.List;

public interface VisitService {
    List<VisitDto> getVisits();
    VisitDto getVisitById(long id);
    VisitDto createVisit(CreateVisitDto visitDto);
    VisitDto updateVisit(long id, UpdateVisitDto visitDto);
}
