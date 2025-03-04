package com.aidr.backend.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SolicitudDemanda")
public class SolicitudDemandaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSolicitudDemanda;

  @ManyToOne
  @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
  private EmpresaEntity empresa;

  @ManyToOne
  @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso")
  private RecursoEntity recurso;

  @Column(name = "fecha", nullable = false)
  private LocalDate fecha;

  @Column(name = "fechaRenovacion", nullable = true)
  private LocalDate fechaRenovacion;

  @Column(name = "tarifa_propuesta", nullable = false)
  private double tarifaPropuesta;

  @Column(name = "tarifa_contra_oferta", nullable = true)
  private double tarifaContraOferta;

  @ManyToOne
  @JoinColumn(name = "idModalidad", referencedColumnName = "idModalidad")
  private ModalidadEntity modalidad;

  @Column(name = "fecha_inicio", nullable = false)
  private LocalDate fechaInicio;

  @Column(name = "fecha_inicio_renovacion", nullable = true)
  private LocalDate fechaInicioRenovacion;

  @Column(name = "fecha_termino", nullable = false)
  private LocalDate fechaTermino;

  @Column(name = "fecha_termino_renovacion", nullable = true)
  private LocalDate fechaTerminoRenovacion;

  @Column(name = "hora_inicio", nullable = false)
  private LocalTime horaInicio;

  @Column(name = "hora_fin", nullable = false)
  private LocalTime horaFin;

  @Column(name = "estatus", nullable = false)
  private boolean estatus;

  @Column(name = "divisa", nullable = false)
  private String divisa;
}
