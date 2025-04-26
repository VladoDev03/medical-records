package com.medical_records.visit_service.service;

import com.medical_records.visit_service.data.entity.Diagnose;
import reactor.core.publisher.Flux;

public interface DiagnoseService {
    Flux<Diagnose> getAllDiagnoses();
}
