package org.example.visitservice.data.repo;

import org.example.visitservice.dto.diagnose.DiagnoseCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.Diagnose;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
    @Query("SELECT DISTINCT v.patientId FROM Visit v JOIN v.diagnoses d WHERE d.id = :diagnoseId")
    List<Long> findAllByDiagnoseId(@Param("diagnoseId") long diagnoseId);

    @Query("""
        SELECT new org.example.visitservice.dto.diagnose.DiagnoseCountDto(
                d.id,
                d.name,
                COUNT(v)
            )
        FROM Visit v
        JOIN v.diagnoses d
        GROUP BY d.id, d.name
        ORDER BY COUNT(v) DESC
    """)
    List<DiagnoseCountDto> findDiagnoseCounts();
}
