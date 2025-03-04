package com.aidr.backend.Specifications.Implements;

import com.aidr.backend.Models.*;
import com.aidr.backend.Specifications.Interfaces.IRecursoSpecification;
import jakarta.persistence.criteria.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecursoSpecificationImpl implements IRecursoSpecification {

    private static final Logger log = LoggerFactory.getLogger(RecursoSpecificationImpl.class);

    @Override
    public Specification<RecursoEntity> nombre(String nombre) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(nombre, "null")) {
                return null;
            }
            return criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
        });
    }

    @Override
    public Specification<RecursoEntity> primerApellido(String primerApellido) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(primerApellido, "null")) {
                return null;
            }
            return criteriaBuilder.like(root.get("primerApellido"), "%" + primerApellido + "%");
        });
    }

    @Override
    public Specification<RecursoEntity> segundoApellido(String segundoApellido) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(segundoApellido, "null")) {
                return null;
            }
            return criteriaBuilder.like(root.get("segundoApellido"), "%" + segundoApellido + "%");
        });
    }

    @Override
    public Specification<RecursoEntity> tarifa(String minI, String maxI) {
        Double min = Double.parseDouble(minI);
        Double max = Double.parseDouble(maxI);
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(minI, maxI)) {
                return null;
            }
            return criteriaBuilder.between(root.get("tarifa"), minI, maxI);
        });
    }

    @Override
    public Specification<RecursoEntity> empresa(Long idEmpresa) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(idEmpresa, 0L)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("empresa").get("idEmpresa"), idEmpresa);
        });
    }

    @Override
    public Specification<RecursoEntity> empresaNotEqual(Long idEmpresa) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(idEmpresa, 0L)) {
                return null;
            }
            return criteriaBuilder.notEqual(root.get("empresa").get("idEmpresa"), idEmpresa);
        });
    }

    @Override
    public Specification<RecursoEntity> modalidad(String modalidad) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(modalidad, "null")) {
                return null;
            }
            return criteriaBuilder.equal(root.get("modalidad").get("modalidad"), modalidad);
        });
    }

    @Override
    public Specification<RecursoEntity> puesto(String puesto) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(puesto, "null")) {
                return null;
            }
            return criteriaBuilder.equal(root.get("puesto").get("puesto"), puesto);
        });
    }

    @Override
    public Specification<RecursoEntity> experiencia(String experiencia) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(experiencia, "null")) {
                return null;
            }
            return criteriaBuilder.equal(root.get("experiencia").get("experiencia"), experiencia);
        });
    }

    @Override
    public Specification<RecursoEntity> tecnologia(String tecnologia) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(tecnologia, "null")) {
                return null;
            }
            Join<RecursoEntity, TecnologiaEntity> tecnologiaJoin = root.join("tecnologias");
            return criteriaBuilder.equal(tecnologiaJoin.get("tecnologia"), tecnologia);
        });
    }

    @Override
    public Specification<RecursoEntity> ocultar(boolean ocultar) {
        return ((root, query, criteriaBuilder) ->
             criteriaBuilder.equal(root.get("ocultar"), ocultar)
        );
    }

    @Override
    public Specification<RecursoEntity> ocultarNoTrue(boolean ocultar) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(ocultar, true)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("ocultar"), ocultar);
        });
    }

    @Override
    public Specification<RecursoEntity> estatus() {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.notEqual(root.get("estatus"), 3);
        });
    }
}
