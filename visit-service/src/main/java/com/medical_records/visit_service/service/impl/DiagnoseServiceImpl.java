package com.medical_records.visit_service.service.impl;

import com.medical_records.visit_service.data.entity.Diagnose;
import com.medical_records.visit_service.data.repository.DiagnoseRepository;
import com.medical_records.visit_service.service.DiagnoseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnoseRepository diagnoseRepository;

    @Override
    public Flux<Diagnose> getAllDiagnoses() {
        return diagnoseRepository.findAll();
    }
}
