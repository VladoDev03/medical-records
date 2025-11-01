package org.example.prescriptionservice.data.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItem {
    private int dosePerIntake;
    private int timesPerDay;
    private int durationDays;
    private BigDecimal price;

    @DBRef
    private Medicine medicine;
}
