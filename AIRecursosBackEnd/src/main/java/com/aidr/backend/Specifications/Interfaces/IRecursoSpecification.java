package com.aidr.backend.Specifications.Interfaces;

import com.aidr.backend.Models.RecursoEntity;
import org.springframework.data.jpa.domain.Specification;

public interface IRecursoSpecification {
    Specification<RecursoEntity> nombre(String nombre);
    Specification<RecursoEntity> primerApellido(String primerApellido);
    Specification<RecursoEntity> segundoApellido(String segundoApellido);
    Specification<RecursoEntity> tarifa(String min, String max);
    Specification<RecursoEntity> empresa(Long idEmpresa);
    Specification<RecursoEntity> empresaNotEqual(Long idEmpresa);
    Specification<RecursoEntity> modalidad(String modalidad);
    Specification<RecursoEntity> puesto(String puesto);
    Specification<RecursoEntity> experiencia(String experiencia);
    Specification<RecursoEntity> tecnologia(String tecnologia);
    Specification<RecursoEntity> ocultar(boolean ocultar);
    Specification<RecursoEntity> ocultarNoTrue(boolean ocultar);
    Specification<RecursoEntity> estatus();
}
