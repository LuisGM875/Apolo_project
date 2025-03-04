package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.NotificacionesXEmpresaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaXDemandaDTO;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import com.aidr.backend.Models.SolicitudesOfertaXDemanda;
import com.aidr.backend.Repositories.ISolicitudDemandaRepository;
import com.aidr.backend.Repositories.ISolicitudOfertaRepository;
import com.aidr.backend.Repositories.ISolicitudesOfertaXDemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudesOfertaXDemandaServiceImpl {

  @Autowired
  ISolicitudesOfertaXDemandaRepository ISolicitudesOfertaXDemandaRepository;
  ISolicitudOfertaRepository isolicitudOfertaRepository;
  ISolicitudDemandaRepository isolicitudDemandaRepository;

  public List<SolicitudOfertaXDemandaDTO> getAllSolicitudesOfertaXDemanda() {
    return ISolicitudesOfertaXDemandaRepository.findAll().stream()
            .map(SolicitudesOXD -> new SolicitudOfertaXDemandaDTO(
                    SolicitudesOXD.getIdSolicitudesOfertaXDemanda(),
                    SolicitudesOXD.getSolicitudOferta(),
                    SolicitudesOXD.getSolicitudDemandaEntity()
            )).collect(Collectors.toList());
  }

  public SolicitudOfertaXDemandaDTO createSolicitudOfertaXDemanda(SolicitudOfertaXDemandaDTO solicitudOfertaXDemandaDTO) {
    SolicitudOfertaEntity solicitudOferta = solicitudOfertaXDemandaDTO.getSolicitudOferta();
    SolicitudDemandaEntity solicitudDemanda = solicitudOfertaXDemandaDTO.getSolicitudDemandaEntity();
    SolicitudesOfertaXDemanda solicitudOfertaXDemanda = SolicitudesOfertaXDemanda.builder()
            .idSolicitudesOfertaXDemanda(solicitudOfertaXDemandaDTO.getIdSolicitudesOfertaXDemanda())
            .SolicitudOferta(solicitudOferta)
            .SolicitudDemandaEntity(solicitudDemanda)
            .build();


    SolicitudesOfertaXDemanda savedSolicitud = ISolicitudesOfertaXDemandaRepository.save(solicitudOfertaXDemanda);

    solicitudOfertaXDemandaDTO.setIdSolicitudesOfertaXDemanda(savedSolicitud.getIdSolicitudesOfertaXDemanda());

    return solicitudOfertaXDemandaDTO;
  }

  public Optional<SolicitudesOfertaXDemanda> getSolicitudOfertaXDemandaById(Long id) {
    return ISolicitudesOfertaXDemandaRepository.findById(id);
  }

  public void saveOrUpdate(SolicitudesOfertaXDemanda solicitudesOfertaXDemanda) {
    ISolicitudesOfertaXDemandaRepository.save(solicitudesOfertaXDemanda);
  }

  public void delete(Long id) {
    ISolicitudesOfertaXDemandaRepository.deleteById(id);
  }

}
