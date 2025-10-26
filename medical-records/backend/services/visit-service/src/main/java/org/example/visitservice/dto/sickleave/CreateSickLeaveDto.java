package org.example.visitservice.dto.sickleave;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSickLeaveDto {
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @Positive
    private int days;

    @NotNull
    private int visitId;
}
