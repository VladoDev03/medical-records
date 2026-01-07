package org.example.visitservice.service.contracts;

import org.example.visitservice.dto.sickleave.CreateSickLeaveDto;
import org.example.visitservice.dto.sickleave.DoctorSickLeaveCountDto;
import org.example.visitservice.dto.sickleave.SickLeaveDto;
import org.example.visitservice.dto.sickleave.SickLeaveMonthCountDto;

import java.util.List;

public interface SickLeaveService {
    List<SickLeaveDto> getSickLeaves();
    SickLeaveDto getSickLeaveById(long id);
    SickLeaveDto createSickLeave(CreateSickLeaveDto sickLeave);
    List<SickLeaveMonthCountDto> getMostActiveSickLeaveMonths(int year);
    List<DoctorSickLeaveCountDto> getDoctorsWithMostSickLeaves();
    void deleteSickLeave(long id);
}
