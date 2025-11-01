package org.example.prescriptionservice.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private String id;
    private String name;
    private String manufacturer;
}
