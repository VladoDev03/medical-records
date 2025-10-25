package org.example.doctorservice.dto.doctor;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDto {
    @NotNull
    private String keycloakId;

    @NotNull
    private List<Long> specialityIds = new ArrayList<>();
}
