package com.aidr.backend.Specifications.Interfaces;

import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import org.springframework.data.jpa.domain.Specification;

public interface ISolicitudOfertaSpecification {
    Specification<SolicitudOfertaEntity> empresa(Long idEmpresa);
    Specification<SolicitudOfertaEntity> modalidad(String modalidad);
    Specification<SolicitudOfertaEntity> puesto(String puesto);
    Specification<SolicitudOfertaEntity> experiencia(String experiencia);
    Specification<SolicitudOfertaEntity> tecnologia(String tecnologia);
    Specification<SolicitudOfertaEntity> tarifa(String min, String max);
}
