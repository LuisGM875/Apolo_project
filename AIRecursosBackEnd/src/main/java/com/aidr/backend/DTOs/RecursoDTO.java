package com.aidr.backend.DTOs;

import com.aidr.backend.Models.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecursoDTO implements Serializable {
    private Long idRecurso;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Set<TecnologiaEntity> tecnologias;
    private ArchivoEntity archivo;
    private String descripcion;
    private Double tarifa;
    private String divisa;
    private EmpresaEntity empresa;
    private int estatus;
    private ModalidadEntity modalidad;
    private ExperienciaEntity experiencia;
    private PuestoEntity puesto;
    private boolean ocultar;
}
