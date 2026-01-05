package org.example.visitservice.dto.sickleave;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorSickLeaveCountDto {
    private long doctorId;
    private long count;
}
