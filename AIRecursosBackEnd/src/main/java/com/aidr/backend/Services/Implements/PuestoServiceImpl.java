package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.PuestoDTO;
import com.aidr.backend.Repositories.IPuestoRepository;
import com.aidr.backend.Services.Interfaces.IPuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuestoServiceImpl implements IPuestoService {

    @Autowired
    IPuestoRepository puestoRepository;

    @Override
    public List<PuestoDTO> findPuestos() {
        return puestoRepository.findAll().stream()
                .map(puesto -> new PuestoDTO(
                        puesto.getIdPuesto(),
                        puesto.getPuesto(),
                        puesto.isEstatus()
                )).collect(Collectors.toList());
    }
}
