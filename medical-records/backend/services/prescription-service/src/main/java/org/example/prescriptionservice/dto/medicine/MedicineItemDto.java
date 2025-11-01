package org.example.prescriptionservice.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItemDto {
    private String medicineName;
    private int dosePerIntake;
    private int timesPerDay;
    private int durationDays;
    private BigDecimal price;
}
