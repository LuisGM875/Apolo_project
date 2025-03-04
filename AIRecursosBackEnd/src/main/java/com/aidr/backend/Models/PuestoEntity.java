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
@Table(name = "puestos")
public class PuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPuesto;

    @Column(name = "puesto", length = 100, nullable = false)
    private String puesto;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

    @JsonIgnore
    @OneToMany(mappedBy = "puesto")
    private Set<RecursoEntity> recursos;

}
