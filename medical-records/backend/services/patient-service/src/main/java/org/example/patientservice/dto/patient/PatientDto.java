package org.example.patientservice.dto.patient;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private long id;
    private String keycloakId;
    private long gpId;
    private LocalDate lastDateInsured;
}
