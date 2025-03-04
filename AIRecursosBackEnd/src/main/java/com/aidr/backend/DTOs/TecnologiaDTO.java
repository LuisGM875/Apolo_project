package com.aidr.backend.DTOs;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TecnologiaDTO implements Serializable {
    private Long idTecnologia;
    private String tecnologia;
    private boolean estatus;

}
