package org.example.visitservice.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.Visit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v LEFT JOIN FETCH v.diagnoses")
    List<Visit> findAllWithDiagnoses();
}
