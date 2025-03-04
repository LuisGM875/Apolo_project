package com.aidr.backend.DTOs;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModalidadDTO implements Serializable {
    private Long idModalidad;
    private String modalidad;
    private boolean estatus;
}
