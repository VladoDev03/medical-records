package org.example.visitservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.visitservice.config.ModelMapperConfig;
import org.example.visitservice.data.entity.Visit;
import org.example.visitservice.data.repo.VisitRepository;
import org.example.visitservice.dto.visit.CreateVisitDto;
import org.example.visitservice.dto.visit.VisitDto;
import org.example.visitservice.exception.EntityNotFoundException;
import org.example.visitservice.service.contracts.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public List<VisitDto> getVisits() {
        return mapperConfig.mapList(visitRepository.findAll(), VisitDto.class);
    }

    @Override
    public VisitDto getVisitById(long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found with id: " + id));

        return mapperConfig.getModelMapper().map(visit, VisitDto.class);
    }

    @Override
    public VisitDto createVisit(CreateVisitDto visit) {
        Visit newVisit = mapperConfig.getModelMapper().map(visit, Visit.class);
        Visit savedVisit = visitRepository.save(newVisit);

        return mapperConfig.getModelMapper().map(savedVisit, VisitDto.class);
    }
}
