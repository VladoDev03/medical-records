package com.medical_records.visit_service.data.repository;

import com.medical_records.visit_service.data.entity.Diagnose;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DiagnoseRepository extends R2dbcRepository<Diagnose, Long> {
}
