package org.example.visitservice.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.Diagnose;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> { }
