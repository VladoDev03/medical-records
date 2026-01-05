package org.example.visitservice.dto.sickleave;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SickLeaveMonthCountDto {
    private int month;
    private long count;
}
