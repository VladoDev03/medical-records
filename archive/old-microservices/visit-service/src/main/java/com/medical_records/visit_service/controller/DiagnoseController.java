package com.medical_records.visit_service.controller;

import com.medical_records.visit_service.data.entity.Diagnose;
import com.medical_records.visit_service.service.DiagnoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnoses")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;

    @GetMapping
    public Flux<Diagnose> getPatients() {
        return diagnoseService.getAllDiagnoses();
    }
}
