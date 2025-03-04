package com.aidr.backend.DTOs;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudOfertaXDemandaDTO {
        private Long idSolicitudesOfertaXDemanda;
        private SolicitudOfertaEntity SolicitudOferta;
        private SolicitudDemandaEntity SolicitudDemandaEntity;
    }

