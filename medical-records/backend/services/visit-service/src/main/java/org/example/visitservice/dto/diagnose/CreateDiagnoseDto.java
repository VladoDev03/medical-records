package org.example.visitservice.dto.diagnose;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.visitservice.validator.InvalidNames;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiagnoseDto {
    @NotBlank(message = "Name must not be blank")
    @InvalidNames(message = "Name contains reserved words")
    private String name;
}
