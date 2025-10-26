package org.example.visitservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SickLeave extends BaseEntity {
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private int days;

    @OneToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = false, unique = true)
    private Visit visit;
}
