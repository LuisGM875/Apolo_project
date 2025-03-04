package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.TecnologiaDTO;
import com.aidr.backend.Models.TecnologiaEntity;

import java.util.List;

public interface ITecnologiaService {

    public List<TecnologiaDTO> findTecnologias();

}
