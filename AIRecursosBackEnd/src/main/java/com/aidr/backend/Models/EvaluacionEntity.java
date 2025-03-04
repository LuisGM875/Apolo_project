package com.aidr.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Evaluaciones")
public class EvaluacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    @ManyToOne()
    @JoinColumn(name = "id_recurso")
    private RecursoEntity recurso;

    @Column(name = "estrella1", nullable = true)
    private int estrella1;

    @Column(name = "estrella2", nullable = true)
    private int estrella2;

    @Column(name = "estrella3", nullable = true)
    private int estrella3;

    @Column(name = "estrella4", nullable = true)
    private int estrella4;

    @Column(name = "estrella5", nullable = true)
    private int estrella5;

    @Column(name = "promedio", nullable = true)
    private float promedio;

    @Column(name = "total_evaluaciones", nullable = true)
    private int totalEvaluaciones;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

}
