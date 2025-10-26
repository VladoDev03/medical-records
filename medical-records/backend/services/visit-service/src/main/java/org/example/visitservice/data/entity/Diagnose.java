package org.example.visitservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Diagnose extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Visit> visits = new HashSet<>();
}
