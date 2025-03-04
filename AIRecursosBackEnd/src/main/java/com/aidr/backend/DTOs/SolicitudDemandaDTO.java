package com.aidr.backend.DTOs;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.ModalidadEntity;
import com.aidr.backend.Models.RecursoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDemandaDTO {
    private Long idSolicitudDemanda;
    private EmpresaEntity empresa;
    private RecursoEntity recurso;
    private LocalDate fecha;
    private LocalDate fechaRenovacion;
    private double tarifaPropuesta;
    private double tarifaContraOferta;
    private ModalidadEntity modalidad;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioRenovacion;
    private LocalDate fechaTermino;
    private LocalDate fechaTerminoRenovacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean estatus;
    private String divisa;
}
