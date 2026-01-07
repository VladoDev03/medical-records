package org.example.prescriptionservice.dto.medicine;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMedicineDto {
    @NotNull
    private String name;

    @NotNull
    private String manufacturer;
}
