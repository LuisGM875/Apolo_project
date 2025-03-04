package com.aidr.backend.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NotificacionesXEmpresa")
public class NotificacionesEmpresaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idNotificacionXEmpresa;


  @ManyToOne
  @JoinColumn(name = "idNotificacion", referencedColumnName = "idNotificacion")
  private CatNotificacionesEntity notificacion;

  @ManyToOne
  @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
  private EmpresaEntity empresa;

  @ManyToOne
  @JoinColumn(name = "idRecurso", referencedColumnName = "idRecurso")
  private RecursoEntity recurso;

  @ManyToOne
  @JoinColumn(name = "idSolicitudesOfertaXDemanda", referencedColumnName = "idSolicitudesOfertaXDemanda")
  private SolicitudesOfertaXDemanda solicitudesOfertaXDemanda;

  private boolean leido;
}
