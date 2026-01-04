package org.example.visitservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.visitservice.config.ModelMapperConfig;
import org.example.visitservice.data.entity.SickLeave;
import org.example.visitservice.data.entity.Visit;
import org.example.visitservice.data.repo.SickLeaveRepository;
import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.sickleave.SickLeaveDto;
import org.example.visitservice.exception.EntityNotFoundException;
import org.example.visitservice.service.contracts.SickLeaveService;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SickLeaveServiceImpl implements SickLeaveService {
    private final SickLeaveRepository sickLeaveRepository;
    private final ModelMapperConfig mapperConfig;
    private final VisitService visitService;

    @Override
    public List<SickLeaveDto> getSickLeaves() {
        return mapperConfig.mapList(sickLeaveRepository.findAll(), SickLeaveDto.class);
    }

    @Override
    public SickLeaveDto getSickLeaveById(long id) {
        SickLeave sickLeave = sickLeaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found with id: " + id));

        return mapperConfig.getModelMapper().map(sickLeave, SickLeaveDto.class);
    }

    @Override
    public SickLeaveDto createSickLeave(CreateSickLeaveDto sickLeave) {
        Visit visit = mapperConfig.getModelMapper()
                .map(visitService.getVisitById(sickLeave.getVisitId()), Visit.class);

        if (sickLeave.getStartDate().isBefore(visit.getVisitDate())) {
            throw new IllegalArgumentException("Sick leave start date cannot be before the visit date");
        }

        SickLeave newSickLeave = mapperConfig.getModelMapper().map(sickLeave, SickLeave.class);
        newSickLeave.setVisit(visit);

        SickLeave savedVisit = sickLeaveRepository.save(newSickLeave);

        return mapperConfig.getModelMapper().map(savedVisit, SickLeaveDto.class);
    }
}
