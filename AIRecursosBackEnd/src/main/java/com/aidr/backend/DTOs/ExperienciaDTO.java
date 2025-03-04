package com.aidr.backend.DTOs;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaDTO implements Serializable {
    private Long idExperiencia;
    private String experiencia;
    private boolean estatus;
}
