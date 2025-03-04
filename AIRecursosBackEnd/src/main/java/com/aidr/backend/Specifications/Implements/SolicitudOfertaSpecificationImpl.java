package com.aidr.backend.Specifications.Implements;

import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import com.aidr.backend.Models.TecnologiaEntity;
import com.aidr.backend.Specifications.Interfaces.ISolicitudOfertaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SolicitudOfertaSpecificationImpl implements ISolicitudOfertaSpecification {

    private static final Logger log = LoggerFactory.getLogger(SolicitudOfertaSpecificationImpl.class);

    @Override
    public Specification<SolicitudOfertaEntity> empresa(Long idEmpresa) {
        return ((root, query, criteriaBuilder) ->
           criteriaBuilder.equal(root.get("empresa").get("idEmpresa"), idEmpresa)
        );
    }

    @Override
    public Specification<SolicitudOfertaEntity> modalidad(String modalidad) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("modalidad").get("modalidad"), modalidad)
        );
    }

    @Override
    public Specification<SolicitudOfertaEntity> puesto(String puesto) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("puesto").get("puesto"), puesto)
        );
    }

    @Override
    public Specification<SolicitudOfertaEntity> experiencia(String experiencia) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("experiencia").get("experiencia"), experiencia)
        );
    }

    @Override
    public Specification<SolicitudOfertaEntity> tecnologia(String tecnologia) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("recurso").get("tecnologias").get("tecnologia"), tecnologia)
        );
    }

    @Override
    public Specification<SolicitudOfertaEntity> tarifa(String min, String max) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("recurso").get("tarifa"), min,max)
        );
    }
}
