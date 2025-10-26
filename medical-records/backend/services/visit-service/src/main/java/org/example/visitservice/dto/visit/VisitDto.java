package org.example.visitservice.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private long id;
    private LocalDate visitDate;
    private long patientId;
    private long doctorId;
}
