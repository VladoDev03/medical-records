package org.example.visitservice.dto.diagnose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnoseCountDto {
    private long diagnoseId;
    private String diagnoseName;
    private long count;
}
