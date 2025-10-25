package org.example.doctorservice.dto.speciality;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.doctorservice.validator.InvalidNames;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecialityDto {
    @NotBlank(message = "Name must not be blank")
    @InvalidNames(message = "Name contains reserved words")
    private String name;
}
