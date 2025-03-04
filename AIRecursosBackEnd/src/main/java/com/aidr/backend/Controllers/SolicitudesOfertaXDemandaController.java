package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.SolicitudDemandaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaXDemandaDTO;
import com.aidr.backend.Models.SolicitudesOfertaXDemanda;
import com.aidr.backend.Services.Implements.SolicitudesOfertaXDemandaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/solicitudes-oferta-demanda")

public class SolicitudesOfertaXDemandaController {

  @Autowired
  private SolicitudesOfertaXDemandaServiceImpl solicitudesOfertaXDemandaServiceImpl;

  @GetMapping
  public List<SolicitudOfertaXDemandaDTO> getAll() {
    return solicitudesOfertaXDemandaServiceImpl.getAllSolicitudesOfertaXDemanda();
  }

  @GetMapping("/{idSolicitudesOfertaXDemanda}")
  public Optional<SolicitudesOfertaXDemanda> getById(@PathVariable("idSolicitudesOfertaXDemanda") long idSolicitudesOfertaXDemanda) {
    return solicitudesOfertaXDemandaServiceImpl.getSolicitudOfertaXDemandaById(idSolicitudesOfertaXDemanda);
  }

  @PostMapping("/solicitudOfertaXDemanda")
  public SolicitudOfertaXDemandaDTO createSolicitudOfertaXDemanda(@RequestBody SolicitudOfertaXDemandaDTO SolicitudOfertaXDemandaDTO) {
    return solicitudesOfertaXDemandaServiceImpl.createSolicitudOfertaXDemanda(SolicitudOfertaXDemandaDTO);
  }

  @DeleteMapping("/{idSolicitudesOfertaXDemanda}")
  public void delete(@PathVariable("idSolicitudesOfertaXDemanda") long idSolicitudesOfertaXDemanda) {
    solicitudesOfertaXDemandaServiceImpl.delete(idSolicitudesOfertaXDemanda);
  }
}
