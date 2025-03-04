package com.aidr.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Recursos")
public class RecursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecurso;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "primer_apellido", length = 50, nullable = false)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 50, nullable = false)
    private String segundoApellido;

    @JoinColumn(name = "id_archivo")
    @OneToOne(cascade = CascadeType.ALL)
    private ArchivoEntity archivo;

    @Column(name = "descripcion", length = 250, nullable = false)
    private String descripcion;

    @Column(name = "tarifa", nullable = false)
    private Double tarifa;

    @Column(name = "divisa", nullable = false)
    private String divisa;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "estatus", nullable = false)
    private int estatus;

    @ManyToOne
    @JoinColumn(name = "id_modalidad", nullable = true)
    private ModalidadEntity modalidad;

    @ManyToOne
    @JoinColumn(name = "id_experiencia", nullable = true)
    private ExperienciaEntity experiencia;

    @ManyToOne
    @JoinColumn(name = "id_puesto", nullable = true)
    private PuestoEntity puesto;

    @Column(name = "ocultar", nullable = false)
    private boolean ocultar;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @JsonIgnore
    @OneToMany(mappedBy = "recurso", cascade = CascadeType.REMOVE)
    private Set<EvaluacionEntity> evaluaciones;

    @ManyToMany(targetEntity = TecnologiaEntity.class, fetch = FetchType.LAZY)
    @JoinTable(name = "recursos_tecnologias",
            joinColumns = @JoinColumn(name = "id_recurso"),
            inverseJoinColumns = @JoinColumn(name = "id_tecnologia"))
    private Set<TecnologiaEntity> tecnologias;

    @JsonIgnoreProperties({"recurso", "empresa"})
    @OneToMany(mappedBy = "recurso")
    private Set<SolicitudOfertaEntity> solicitudOfertas;
}
