package org.example.doctorservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Speciality extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
}
