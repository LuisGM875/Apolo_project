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
@Table(name = "archivos")
public class ArchivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArchivo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "archivo", length = 100000, nullable = false)
    private byte[] archivo;

    @JsonIgnore
    @OneToOne(mappedBy = "archivo")
    private RecursoEntity recurso;

    @Column(name = "estatus", nullable = false)
    private boolean estatus;

}
