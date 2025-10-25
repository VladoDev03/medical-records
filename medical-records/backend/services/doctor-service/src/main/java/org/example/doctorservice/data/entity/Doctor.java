package org.example.doctorservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends BaseEntity {
    @Column(name = "keycloak_id", nullable = false, unique = true)
    private String keycloakId;
}
