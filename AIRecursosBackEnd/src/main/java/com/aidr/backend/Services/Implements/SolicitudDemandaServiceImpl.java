package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.DTOs.SolicitudDemandaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import com.aidr.backend.Repositories.ISolicitudDemandaRepository;
import com.aidr.backend.Services.Interfaces.ISolicitudDemandaService;
import com.aidr.backend.Specifications.Implements.RecursoSpecificationImpl;
import com.aidr.backend.Specifications.Implements.SolicitudDemandaSpecificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudDemandaServiceImpl implements ISolicitudDemandaService {

  @Autowired
  ISolicitudDemandaRepository solicitudDemandaRepository;
  @Autowired
  private SolicitudDemandaSpecificationImpl solicitudDemandaSpecification;

  @Override
  public SolicitudDemandaDTO createSolicitudDemanda(SolicitudDemandaDTO solicitudDemandaDTO) {
    SolicitudDemandaEntity solicitudDemanda = SolicitudDemandaEntity.builder()
            .idSolicitudDemanda(solicitudDemandaDTO.getIdSolicitudDemanda())
            .empresa(solicitudDemandaDTO.getEmpresa())
            .recurso(solicitudDemandaDTO.getRecurso())
            .fecha(LocalDate.now())
            .fechaRenovacion(solicitudDemandaDTO.getFechaRenovacion())
            .tarifaPropuesta(solicitudDemandaDTO.getTarifaPropuesta())
            .tarifaContraOferta(solicitudDemandaDTO.getTarifaContraOferta())
            .modalidad(solicitudDemandaDTO.getModalidad())
            .fechaInicio(solicitudDemandaDTO.getFechaInicio())
            .fechaInicioRenovacion(solicitudDemandaDTO.getFechaInicioRenovacion())
            .fechaTermino(solicitudDemandaDTO.getFechaTermino())
            .fechaTerminoRenovacion(solicitudDemandaDTO.getFechaTerminoRenovacion())
            .horaInicio(solicitudDemandaDTO.getHoraInicio())
            .horaFin(solicitudDemandaDTO.getHoraFin())
            .estatus(solicitudDemandaDTO.isEstatus())
            .divisa(solicitudDemandaDTO.getDivisa())
            .build();
    SolicitudDemandaEntity savedSolicitud = solicitudDemandaRepository.save(solicitudDemanda);

    solicitudDemandaDTO.setIdSolicitudDemanda(savedSolicitud.getIdSolicitudDemanda());
    return solicitudDemandaDTO;
  }


  @Override
  public List<SolicitudDemandaDTO> findSolicitudesDemandaByEmpresa(Long idEmpresa, boolean estatus) {
    Specification<SolicitudDemandaEntity> spec = Specification.where(null);
    if (!Objects.equals(idEmpresa, 0L)) {
      spec = spec.and(solicitudDemandaSpecification.empresa(idEmpresa));
      spec = spec.and(solicitudDemandaSpecification.estatus(estatus));
      return solicitudDemandaRepository.findAll(spec).stream()
              .map(solicitudDemanda -> new SolicitudDemandaDTO(
                      solicitudDemanda.getIdSolicitudDemanda(),
                      solicitudDemanda.getEmpresa(),
                      solicitudDemanda.getRecurso(),
                      solicitudDemanda.getFecha(),
                      solicitudDemanda.getFechaRenovacion(),
                      solicitudDemanda.getTarifaPropuesta(),
                      solicitudDemanda.getTarifaContraOferta(),
                      solicitudDemanda.getModalidad(),
                      solicitudDemanda.getFechaInicio(),
                      solicitudDemanda.getFechaInicioRenovacion(),
                      solicitudDemanda.getFechaTermino(),
                      solicitudDemanda.getFechaTerminoRenovacion(),
                      solicitudDemanda.getHoraInicio(),
                      solicitudDemanda.getHoraFin(),
                      solicitudDemanda.isEstatus(),
                      solicitudDemanda.getDivisa()
              )).collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public List<SolicitudDemandaDTO> findSolicitudesDemandaByFiltros(Long idEmpresa, String puesto, String tecnologia, String modalidad, String experiencia, String min, String max) {
    Specification<SolicitudDemandaEntity> spec = Specification.where(null);
    if (!Objects.equals(idEmpresa, 0L)) {
      spec = spec.and(solicitudDemandaSpecification.empresa(idEmpresa));
    }
    if (!Objects.equals(puesto, "null")) {
      spec = spec.and(solicitudDemandaSpecification.puesto(puesto));
    }
    if (!Objects.equals(modalidad, "null")) {
      spec = spec.and(solicitudDemandaSpecification.modalidad(modalidad));
    }
    if (!Objects.equals(experiencia, "null")) {
      spec = spec.and(solicitudDemandaSpecification.experiencia(experiencia));
    }
    if (!Objects.equals(min, max)) {
      spec = spec.and(solicitudDemandaSpecification.tarifa(min, max));
    }
    if (!Objects.equals(tecnologia, "null")) {
      spec = spec.and(solicitudDemandaSpecification.tecnologia(tecnologia));
    }
    return solicitudDemandaRepository.findAll(spec).stream()
            .map(solicitudDemanda -> new SolicitudDemandaDTO(
                    solicitudDemanda.getIdSolicitudDemanda(),
                    solicitudDemanda.getEmpresa(),
                    solicitudDemanda.getRecurso(),
                    solicitudDemanda.getFecha(),
                    solicitudDemanda.getFechaRenovacion(),
                    solicitudDemanda.getTarifaPropuesta(),
                    solicitudDemanda.getTarifaContraOferta(),
                    solicitudDemanda.getModalidad(),
                    solicitudDemanda.getFechaInicio(),
                    solicitudDemanda.getFechaInicioRenovacion(),
                    solicitudDemanda.getFechaTermino(),
                    solicitudDemanda.getFechaTerminoRenovacion(),
                    solicitudDemanda.getHoraInicio(),
                    solicitudDemanda.getHoraFin(),
                    solicitudDemanda.isEstatus(),
                    solicitudDemanda.getDivisa()
            )).collect(Collectors.toList());
  }
}
