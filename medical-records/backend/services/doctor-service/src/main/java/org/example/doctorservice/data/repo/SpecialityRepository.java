package org.example.doctorservice.data.repo;

import org.example.doctorservice.data.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> { }
