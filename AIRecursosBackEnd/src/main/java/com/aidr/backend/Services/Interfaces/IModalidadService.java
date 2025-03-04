package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.ModalidadDTO;

import java.util.List;

public interface IModalidadService {
    public List<ModalidadDTO> findModalidades();
}
