package com.aidr.backend.Specifications.Implements;

import com.aidr.backend.Models.SolicitudDemandaEntity;
import com.aidr.backend.Specifications.Interfaces.ISolicitudDemandaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SolicitudDemandaSpecificationImpl implements ISolicitudDemandaSpecification {

    private static final Logger log = LoggerFactory.getLogger(SolicitudDemandaSpecificationImpl.class);

    @Override
    public Specification<SolicitudDemandaEntity> empresa(Long idEmpresa) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("empresa").get("idEmpresa"), idEmpresa)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> estatus(boolean estatus) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("estatus"), estatus)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> modalidad(String modalidad) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("modalidad").get("modalidad"), modalidad)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> puesto(String puesto) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("puesto").get("puesto"), puesto)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> experiencia(String experiencia) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("experiencia").get("experiencia"), experiencia)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> tecnologia(String tecnologia) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("tecnologias").get("tecnologia"), tecnologia)
        );
    }

    @Override
    public Specification<SolicitudDemandaEntity> tarifa(String min, String max) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("recurso").get("tarifa"), min,max)
        );
    }

}
