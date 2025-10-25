package org.example.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private long id;
    private String keycloakId;
    private String gpId;
    private LocalDate lastDateInsured;
}
