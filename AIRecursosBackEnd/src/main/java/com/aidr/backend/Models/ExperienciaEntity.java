package com.aidr.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experiencias")
public class ExperienciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperiencia;

    @Column(name = "experiencia", length = 50,nullable = false)
    private String experiencia;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

    @JsonIgnore
    @OneToMany(mappedBy = "experiencia")
    private Set<RecursoEntity> recursos;

}
