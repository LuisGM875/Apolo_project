package com.aidr.backend.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CAT-Notificaciones")
public class CatNotificacionesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idNotificacion;

  @ManyToOne
  @JoinColumn(name = "idTipoDeNotificacion", referencedColumnName = "idTipoNotificacion")
  private CatTiposnotificacionesEntity tipoNotificacion;

  private String texto;

  private int estatus;


}

