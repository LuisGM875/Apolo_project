package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.SolicitudOfertaDTO;
import com.aidr.backend.Services.Implements.SolicitudOfertaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/r")

public class SolicitudOfertaController {

  @Autowired
  private SolicitudOfertaServiceImpl solicitudOfertaService;

  @PostMapping("/solicitudOferta")
  public SolicitudOfertaDTO createSolicitudOferta(@RequestBody SolicitudOfertaDTO solicitudOfertaDTO) {
    return solicitudOfertaService.createSolicitudOferta(solicitudOfertaDTO);
  }

  @GetMapping("/solicitudesOferta")
  public List<SolicitudOfertaDTO> findSolicitudesOferta() {
    return solicitudOfertaService.findSolicitudesOferta();
  }

  @GetMapping("/solicitudesOferta/empresa")
  public List<SolicitudOfertaDTO> findSolicitudesOfertaByEmpresa(@RequestParam(name = "idEmpresa") Long idEmpresa, @RequestParam(name = "estatus") boolean estatus) {
    return solicitudOfertaService.findSolicitudesOfertaByEmpresa(idEmpresa, estatus);
  }

  @GetMapping("/solicitudesOferta/empresa/filtro")
  public List<SolicitudOfertaDTO> findSolicitudesOfertaByFiltros(@RequestParam("idEmpresa") Long idEmpresa, @RequestParam("puesto") String puesto, @RequestParam("tecnologia") String tecnologia , @RequestParam("modalidad") String modalidad, @RequestParam("experiencia") String experiencia, @RequestParam("min") String min, @RequestParam("max") String max) {
    return solicitudOfertaService.findSolicitudesOfertaByFiltros(idEmpresa, puesto, tecnologia, modalidad, experiencia, min, max);
  }

}
