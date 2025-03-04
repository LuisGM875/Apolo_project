package com.aidr.backend.Specifications.Interfaces;

import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import org.springframework.data.jpa.domain.Specification;

public interface ISolicitudDemandaSpecification {
    Specification<SolicitudDemandaEntity> empresa(Long idEmpresa);
    Specification<SolicitudDemandaEntity> estatus(boolean estatus);
    Specification<SolicitudDemandaEntity> modalidad(String modalidad);
    Specification<SolicitudDemandaEntity> puesto(String puesto);
    Specification<SolicitudDemandaEntity> experiencia(String experiencia);
    Specification<SolicitudDemandaEntity> tecnologia(String tecnologia);
    Specification<SolicitudDemandaEntity> tarifa(String min, String max);

}
