package org.example.patientservice.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchPatientDto {
    private List<PatientDto> patientDtoList;
    private String message;
}
