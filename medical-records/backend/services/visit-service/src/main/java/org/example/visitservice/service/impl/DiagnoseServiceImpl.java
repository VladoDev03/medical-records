package org.example.visitservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.visitservice.config.ModelMapperConfig;
import org.example.visitservice.data.entity.Diagnose;
import org.example.visitservice.data.repo.DiagnoseRepository;
import org.example.visitservice.dto.diagnose.CreateDiagnoseDto;
import org.example.visitservice.dto.diagnose.DiagnoseDto;
import org.example.visitservice.exception.EntityNotFoundException;
import org.example.visitservice.service.contracts.DiagnoseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnoseRepository diagnoseRepository;
    private final ModelMapperConfig mapperConfig;

    @Override
    public List<DiagnoseDto> getDiagnoses() {
        return mapperConfig.mapList(diagnoseRepository.findAll(), DiagnoseDto.class);
    }

    @Override
    public DiagnoseDto getDiagnoseById(long id) {
        Diagnose diagnose = diagnoseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnose not found with id: " + id));

        return mapperConfig.getModelMapper().map(diagnose, DiagnoseDto.class);
    }

    @Override
    public DiagnoseDto createDiagnose(CreateDiagnoseDto diagnose) {
        Diagnose newDiagnose = mapperConfig.getModelMapper().map(diagnose, Diagnose.class);
        Diagnose savedDiagnose = diagnoseRepository.save(newDiagnose);

        return mapperConfig.getModelMapper().map(savedDiagnose, DiagnoseDto.class);
    }
}
