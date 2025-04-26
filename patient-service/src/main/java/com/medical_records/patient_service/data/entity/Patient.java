package com.medical_records.patient_service.data.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity {
    private String keycloakId;
    private String gpId;
    private LocalDate lastDateInsured;
}
