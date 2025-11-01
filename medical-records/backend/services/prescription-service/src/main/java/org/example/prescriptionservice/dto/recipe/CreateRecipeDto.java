package org.example.prescriptionservice.dto.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.prescriptionservice.dto.medicine.CreateMedicineItemDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDto {
    @NotBlank
    private String prescriptionCode;

    @PastOrPresent(message = "Date created date cannot be in the future")
    private LocalDate dateCreated;

    @NotNull
    private long doctorId;

    @NotNull
    private long patientId;

    @NotNull
    private long visitId;

    @NotNull
    @NotEmpty
    private List<CreateMedicineItemDto> medicines;
}
