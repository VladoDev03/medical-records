package org.example.visitservice.data.repo;

import org.example.visitservice.dto.visit.DoctorVisitCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.Visit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v LEFT JOIN FETCH v.diagnoses")
    List<Visit> findAllWithDiagnoses();

    @Query("SELECT v FROM Visit v LEFT JOIN FETCH v.diagnoses WHERE v.patientId = :patientId")
    List<Visit> findAllByPatientId(Long patientId);

    @Query("SELECT new org.example.visitservice.dto.visit.DoctorVisitCountDto(v.doctorId, COUNT(v)) " +
            "FROM Visit v GROUP BY v.doctorId")
    List<DoctorVisitCountDto> countVisitsGroupedByDoctor();
}
