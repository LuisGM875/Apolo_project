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
@Table(name = "modalidades")
public class ModalidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModalidad;

    @Column(name = "modalidad", length = 50, nullable = false)
    private String modalidad;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

    @JsonIgnore
    @OneToMany(mappedBy = "modalidad")
    private Set<RecursoEntity> recursos;

}
