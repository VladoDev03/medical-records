package org.example.visitservice.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVisitCountDto {
    private long doctorId;
    private long visitCount;
}
