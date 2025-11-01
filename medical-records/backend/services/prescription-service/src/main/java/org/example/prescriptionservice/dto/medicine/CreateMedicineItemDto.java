package org.example.prescriptionservice.dto.medicine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicineItemDto {
    @NotBlank
    private String medicineId;

    @Positive
    private int dosePerIntake;

    @Positive
    private int timesPerDay;

    @Positive
    private int durationDays;

    @NotNull
    @Positive
    private BigDecimal price;
}
