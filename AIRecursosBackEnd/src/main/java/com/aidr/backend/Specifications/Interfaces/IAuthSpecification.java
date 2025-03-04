package com.aidr.backend.Specifications.Interfaces;

import com.aidr.backend.Models.EmpresaEntity;
import org.springframework.data.jpa.domain.Specification;

public interface IAuthSpecification {
    Specification<EmpresaEntity> correo (String correo);
    Specification<EmpresaEntity> contrasena (String contrasena);
}
