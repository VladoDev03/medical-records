package org.example.patientservice.dto.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientDto {
    @NotNull
    private String keycloakId;

    @NotNull
    private long gpId;

    @NotNull
    @PastOrPresent(message = "Last insured date cannot be in the future")
    private LocalDate lastDateInsured;
}
