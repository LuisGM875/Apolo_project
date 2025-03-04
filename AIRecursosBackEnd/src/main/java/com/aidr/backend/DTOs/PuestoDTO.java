package com.aidr.backend.DTOs;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PuestoDTO implements Serializable {
    private Long idPuesto;
    private String puesto;
    private boolean estatus;
}
