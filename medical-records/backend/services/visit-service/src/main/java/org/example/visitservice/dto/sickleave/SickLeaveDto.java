package org.example.visitservice.dto.sickleave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SickLeaveDto {
    private long id;
    private LocalDate startDate;
    private int days;
    private int visitId;
}
