package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.SolicitudOfertaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import com.aidr.backend.Repositories.ISolicitudOfertaRepository;
import com.aidr.backend.Services.Interfaces.ISolicitudOfertaService;
import com.aidr.backend.Specifications.Implements.SolicitudOfertaSpecificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SolicitudOfertaServiceImpl implements ISolicitudOfertaService {

  @Autowired
  ISolicitudOfertaRepository solicitudOfertaRepository;
  @Autowired
  SolicitudOfertaSpecificationImpl solicitudOfertaSpecification;

  @Override
  public SolicitudOfertaDTO createSolicitudOferta(SolicitudOfertaDTO solicitudOfertaDTO) {
    SolicitudOfertaEntity solicitudOferta = SolicitudOfertaEntity.builder()
            .idSolicitudOferta(solicitudOfertaDTO.getIdSolicitudOferta())
            .empresa(solicitudOfertaDTO.getEmpresa())
            .recurso(solicitudOfertaDTO.getRecurso())
            .fecha(LocalDate.now())
            .build();
    SolicitudOfertaEntity savedSolicitud = solicitudOfertaRepository.save(solicitudOferta);

    solicitudOfertaDTO.setIdSolicitudOferta(savedSolicitud.getIdSolicitudOferta());
    return solicitudOfertaDTO;
  }

  @Override
  public List<SolicitudOfertaDTO> findSolicitudesOferta() {
    return solicitudOfertaRepository.findAll().stream()
            .map(solicitudOferta -> new SolicitudOfertaDTO(
                    solicitudOferta.getIdSolicitudOferta(),
                    solicitudOferta.getEmpresa(),
                    solicitudOferta.getRecurso(),
                    solicitudOferta.getFecha()
            )).collect(Collectors.toList());
  }

  @Override
  public List<SolicitudOfertaDTO> findSolicitudesOfertaByEmpresa(Long idEmpresa, boolean estatus) {
    Specification<SolicitudOfertaEntity> spec = Specification.where(null);
    if (!Objects.equals(idEmpresa, 0L)) {
      spec = spec.and(solicitudOfertaSpecification.empresa(idEmpresa));
      return solicitudOfertaRepository.findAll(spec).stream()
              .map(solicitudOferta -> new SolicitudOfertaDTO(
                      solicitudOferta.getIdSolicitudOferta(),
                      solicitudOferta.getEmpresa(),
                      solicitudOferta.getRecurso(),
                      solicitudOferta.getFecha()
              )).collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public List<SolicitudOfertaDTO> findSolicitudesOfertaByFiltros(Long idEmpresa, String puesto, String tecnologia, String modalidad, String experiencia, String min, String max) {
    Specification<SolicitudOfertaEntity> spec = Specification.where(null);
    if (!Objects.equals(idEmpresa, 0L)) {
      spec = spec.and(solicitudOfertaSpecification.empresa(idEmpresa));
    }
    if (!Objects.equals(puesto, "null")) {
      spec = spec.and(solicitudOfertaSpecification.puesto(puesto));
    }
    if (!Objects.equals(modalidad, "null")) {
      spec = spec.and(solicitudOfertaSpecification.modalidad(modalidad));
    }
    if (!Objects.equals(experiencia, "null")) {
      spec = spec.and(solicitudOfertaSpecification.experiencia(experiencia));
    }
    if (!Objects.equals(min, max)) {
      spec = spec.and(solicitudOfertaSpecification.tarifa(min, max));
    }
    if (!Objects.equals(tecnologia, "null")) {
      spec = spec.and(solicitudOfertaSpecification.tecnologia(tecnologia));
    }

    return solicitudOfertaRepository.findAll(spec).stream()
            .map(solicitudOferta -> new SolicitudOfertaDTO(
                    solicitudOferta.getIdSolicitudOferta(),
                    solicitudOferta.getEmpresa(),
                    solicitudOferta.getRecurso(),
                    solicitudOferta.getFecha()
            )).collect(Collectors.toList());
  }
}

