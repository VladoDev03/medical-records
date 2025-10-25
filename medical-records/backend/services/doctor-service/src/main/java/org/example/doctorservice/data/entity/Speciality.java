package org.example.doctorservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Speciality extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "specialities")
    private Set<Doctor> doctors = new HashSet<>();
}
