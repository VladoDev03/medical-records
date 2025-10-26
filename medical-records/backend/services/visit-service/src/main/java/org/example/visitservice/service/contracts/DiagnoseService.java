package org.example.visitservice.service.contracts;

import org.example.visitservice.dto.diagnose.CreateDiagnoseDto;
import org.example.visitservice.dto.diagnose.DiagnoseDto;

import java.util.List;

public interface DiagnoseService {
    List<DiagnoseDto> getDiagnoses();
    List<DiagnoseDto> getDiagnosesByIds(List<Long> diagnoseIds);
    DiagnoseDto getDiagnoseById(long id);
    DiagnoseDto createDiagnose(CreateDiagnoseDto diagnose);
}
