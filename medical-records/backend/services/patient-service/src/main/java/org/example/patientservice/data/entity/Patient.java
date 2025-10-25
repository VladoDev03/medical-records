package org.example.patientservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keycloak_id", nullable = false, unique = true)
    private String keycloakId;


    @Column(name = "GP_id", nullable = false)
    private String gpId;

    @Column(name = "last_date_insured", nullable = false)
    private LocalDate lastDateInsured;
}
