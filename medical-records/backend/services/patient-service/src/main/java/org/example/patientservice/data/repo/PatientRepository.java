package org.example.patientservice.data.repo;

import org.example.patientservice.data.entity.Patient;
import org.example.patientservice.dto.patient.GpPatientCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByGpId(long gpId);

    @Query("SELECT p.gpId AS gpId, COUNT(p) AS patientCount " +
            "FROM Patient p GROUP BY p.gpId")
    List<GpPatientCountDto> countPatientsGroupedByGp();
}
