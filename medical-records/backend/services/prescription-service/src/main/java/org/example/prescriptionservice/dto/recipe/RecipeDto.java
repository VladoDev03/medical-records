package org.example.prescriptionservice.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.prescriptionservice.dto.medicine.MedicineItemDto;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class RecipeDto {
    private String id;
    private String prescriptionCode;
    private LocalDate dateCreated;
    private long doctorId;
    private long patientId;
    private long visitId;
    private List<MedicineItemDto> medicines;
}
