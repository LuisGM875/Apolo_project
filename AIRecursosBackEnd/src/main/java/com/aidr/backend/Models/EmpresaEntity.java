package com.aidr.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Empresas")
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(name = "nombre", length = 50, nullable = true)
    private String nombre;

    @Column(name = "correo_electronico", length = 100, nullable = true)
    private String correoElectronico;

    @Column(name = "contrasena", length = 100, nullable = true)
    private String contrasena;

    @Column(name = "razon_social", length = 100, nullable = true)
    private String razonSocial;

    @Column(name = "domicilio", length = 300, nullable = true)
    private String domicilio;

    @Column(name = "repse_folio", length = 100, nullable = true)
    private String repseFolio;

    @Column(name = "repse_pdf", length = 1000000, nullable = true)
    private byte[] repsePDF;

    @Column(name = "numero_contacto", length = 100, nullable = true)
    private String numeroContacto;

    @Column(name = "logo", nullable = true, length = 10000000)
    private byte[] logo;

    @Column(name = "estatus", nullable = true)
    private boolean estatus;

    @Column(name = "contrasena_archivo", length = 100, nullable = true)
    private String contrasenaArchivo;

    @Column(name = "fecha_registro", nullable = true)
    private LocalDate fechaRegistro;

    @Column(name = "rfc", length = 100, nullable = true)
    private String rfc;

    @Column(name = "jtw", length = 300, nullable = true)
    private String jwt;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private Set<RecursoEntity> recursos;

    @JsonIgnoreProperties({"empresa", "recurso"})
    @OneToMany(mappedBy = "empresa")
    private Set<SolicitudOfertaEntity> solicitudOfertas;

}
