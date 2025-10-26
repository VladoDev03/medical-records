package org.example.visitservice.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Visit extends BaseEntity {
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "patient_id", nullable = false)
    private long patientId;

    @Column(name = "doctor_id", nullable = false)
    private long doctorId;

    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
    private SickLeave sickLeave;

    @ManyToMany
    @JoinTable(
            name = "visit_has_diagnoses",
            joinColumns = @JoinColumn(name = "visit_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id")
    )
    private Set<Diagnose> diagnoses = new HashSet<>();
}
