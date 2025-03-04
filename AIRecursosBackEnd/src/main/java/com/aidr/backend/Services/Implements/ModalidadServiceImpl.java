package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.ModalidadDTO;
import com.aidr.backend.Repositories.IModalidadRepository;
import com.aidr.backend.Services.Interfaces.IModalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModalidadServiceImpl implements IModalidadService {

    @Autowired
    IModalidadRepository modalidadRepository;

    @Override
    public List<ModalidadDTO> findModalidades() {
        return modalidadRepository.findAll().stream()
                .map(modalidad -> new ModalidadDTO(
                        modalidad.getIdModalidad(),
                        modalidad.getModalidad(),
                        modalidad.isEstatus()
                )).collect(Collectors.toList());
    }
}
