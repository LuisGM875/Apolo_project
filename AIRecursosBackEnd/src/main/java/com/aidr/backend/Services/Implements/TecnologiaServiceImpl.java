package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.TecnologiaDTO;
import com.aidr.backend.Models.TecnologiaEntity;
import com.aidr.backend.Repositories.ITecnologiaRepository;
import com.aidr.backend.Services.Interfaces.ITecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnologiaServiceImpl implements ITecnologiaService {

    @Autowired
    private ITecnologiaRepository tecnologiaRepository;

    @Override
    public List<TecnologiaDTO> findTecnologias() {
        return tecnologiaRepository.findAll().stream()
                .map(tecnologia -> new TecnologiaDTO(
                        tecnologia.getIdTecnologia(),
                        tecnologia.getTecnologia(),
                        tecnologia.isEstatus()
                )).collect(Collectors.toList());
    }
}
