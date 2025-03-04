package com.aidr.backend.DTOs;

import com.aidr.backend.Models.CatNotificacionesEntity;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudesOfertaXDemanda;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionesXEmpresaDTO {

    private Long idNotificacionXEmpresa;
    private CatNotificacionesEntity notificacion;
    private EmpresaEntity empresa;
    private RecursoEntity recurso;
    private SolicitudesOfertaXDemanda solicitudesOfertaXDemanda;
    private boolean leido;
}
