package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.ExperienciaDTO;
import com.aidr.backend.Repositories.IExperienciaRepository;
import com.aidr.backend.Services.Interfaces.IExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienciaServiceImpl implements IExperienciaService {

    @Autowired
    IExperienciaRepository experienciaRepository;

    @Override
    public List<ExperienciaDTO> findExperiencias() {
        return experienciaRepository.findAll().stream()
                .map(experiencia -> new ExperienciaDTO(
                        experiencia.getIdExperiencia(),
                        experiencia.getExperiencia(),
                        experiencia.isEstatus()
                )).collect(Collectors.toList());
    }
}
