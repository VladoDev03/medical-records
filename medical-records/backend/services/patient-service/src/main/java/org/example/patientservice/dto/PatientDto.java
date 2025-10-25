package org.example.patientservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private long id;
    private String keycloakId;
    private String gpId;
    private LocalDate lastDateInsured;
}
