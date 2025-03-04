package com.aidr.backend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SolicitudOferta")
public class SolicitudOfertaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSolicitudOferta;

  @ManyToOne
  @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
  private EmpresaEntity empresa;

  @ManyToOne
  @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso")
  private RecursoEntity recurso;

  @Column(name = "fecha", nullable = false)
  private LocalDate fecha;
}
