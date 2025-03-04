package com.aidr.backend.Models;
import com.aidr.backend.DTOs.SolicitudOfertaDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SolicitudOfertaXDemanda")
public class SolicitudesOfertaXDemanda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSolicitudesOfertaXDemanda;


  @ManyToOne
  @JoinColumn(name = "idSolicitudOferta", referencedColumnName = "idSolicitudOferta")
  private SolicitudOfertaEntity SolicitudOferta;


  @ManyToOne
  @JoinColumn(name = "idSolicitudDemanda", referencedColumnName = "idSolicitudDemanda")
  private SolicitudDemandaEntity SolicitudDemandaEntity;

}
