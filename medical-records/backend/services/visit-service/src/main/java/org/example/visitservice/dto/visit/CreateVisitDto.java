package org.example.visitservice.dto.visit;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVisitDto {
    @FutureOrPresent(message = "Visit date cannot be in the past")
    private LocalDate visitDate;

    @NotNull
    private long patientId;

    @NotNull
    private long doctorId;
}
