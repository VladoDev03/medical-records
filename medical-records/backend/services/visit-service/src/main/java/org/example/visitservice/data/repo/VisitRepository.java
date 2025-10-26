package org.example.visitservice.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.visitservice.data.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> { }
