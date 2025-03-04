package com.aidr.backend.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tecnologias")
public class TecnologiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnologia;

    @Column(name = "tecnologia", length = 50, nullable = false)
    private String tecnologia;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

}
