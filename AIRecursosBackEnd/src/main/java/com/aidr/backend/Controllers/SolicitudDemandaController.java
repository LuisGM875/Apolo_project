package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.DTOs.SolicitudDemandaDTO;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Services.Implements.SolicitudDemandaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/r")

public class SolicitudDemandaController {

    @Autowired
    SolicitudDemandaServiceImpl solicitudDemandaService;

    @PostMapping("/solicitudDemanda")
    public SolicitudDemandaDTO createSolicitudDemanda(@RequestBody SolicitudDemandaDTO solicitudDemandaDTO) {
        return solicitudDemandaService.createSolicitudDemanda(solicitudDemandaDTO);
    }

    @GetMapping("/solicitudesDemanda/empresa")
    public List<SolicitudDemandaDTO> findSolicitudesDemandaByEmpresa(@RequestParam(name = "idEmpresa") Long idEmpresa, @RequestParam(name = "estatus") boolean estatus) {
        return solicitudDemandaService.findSolicitudesDemandaByEmpresa(idEmpresa, estatus);
    }

    @GetMapping("/solicitudesDemanda/empresa/filtro")
    public List<SolicitudDemandaDTO> findSolicitudesDemandaByFiltros(@RequestParam("idEmpresa") Long idEmpresa, @RequestParam("puesto") String puesto, @RequestParam("tecnologia") String tecnologia , @RequestParam("modalidad") String modalidad, @RequestParam("experiencia") String experiencia, @RequestParam("min") String min, @RequestParam("max") String max) {
        return solicitudDemandaService.findSolicitudesDemandaByFiltros(idEmpresa, puesto, tecnologia, modalidad, experiencia, min, max);
    }

}
