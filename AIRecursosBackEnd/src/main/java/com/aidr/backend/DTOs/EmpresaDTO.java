package com.aidr.backend.DTOs;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO implements Serializable {
    private Long idEmpresa;
    private String nombre;
    private String contrasena;
    private String correoElectronico;
    private String rfc;
    private String razonSocial;
    private String domicilio;
    private String repseFolio;
    private byte[] repsePDF;
    private String numeroContacto;
    private String contrasenaArchivo;
    private byte[] logo;
    private LocalDate fechaRegistro;
    private boolean estatus;
    private String jwt;
}
