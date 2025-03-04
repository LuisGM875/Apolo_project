package com.aidr.backend.DTOs;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudOfertaDTO implements Serializable {
    private Long idSolicitudOferta;
    private EmpresaEntity empresa;
    private RecursoEntity recurso;
    private LocalDate fecha;
}
