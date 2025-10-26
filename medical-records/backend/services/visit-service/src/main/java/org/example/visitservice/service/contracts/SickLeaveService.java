package org.example.visitservice.service.contracts;

import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.sickleave.SickLeaveDto;

import java.util.List;

public interface SickLeaveService {
    List<SickLeaveDto> getSickLeaves();
    SickLeaveDto getSickLeaveById(long id);
    SickLeaveDto createSickLeave(CreateSickLeaveDto sickLeave);
}
