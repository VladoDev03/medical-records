package org.example.visitservice.data.repo;

import org.example.visitservice.dto.sickleave.DoctorSickLeaveCountDto;
import org.example.visitservice.dto.sickleave.SickLeaveMonthCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.SickLeave;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {

    @Query("""
        SELECT new org.example.visitservice.dto.sickleave.SickLeaveMonthCountDto(
            MONTH(s.startDate),
            COUNT(s)
        )
        FROM SickLeave s
        WHERE YEAR(s.startDate) = :year
        GROUP BY MONTH(s.startDate)
        ORDER BY COUNT(s) DESC
    """)
    List<SickLeaveMonthCountDto> countSickLeavesByMonth(int year);

    @Query("""
        SELECT new org.example.visitservice.dto.sickleave.DoctorSickLeaveCountDto(
            v.doctorId,
            COUNT(s)
        )
        FROM SickLeave s
        JOIN s.visit v
        GROUP BY v.doctorId
        ORDER BY COUNT(s) DESC
    """)
    List<DoctorSickLeaveCountDto> countSickLeavesByDoctor();
}

