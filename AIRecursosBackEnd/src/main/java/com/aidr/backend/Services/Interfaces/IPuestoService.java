package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.PuestoDTO;

import java.util.List;

public interface IPuestoService {
    public List<PuestoDTO> findPuestos();
}
