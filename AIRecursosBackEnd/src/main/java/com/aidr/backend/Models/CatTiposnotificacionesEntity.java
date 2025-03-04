package com.aidr.backend.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cat-TiposNotificaciones")
public class CatTiposnotificacionesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idTipoNotificacion;

  private String tipoNotificacion;

  private int estatus;

}


