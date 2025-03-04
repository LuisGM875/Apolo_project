package com.aidr.backend.Specifications.Implements;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Specifications.Interfaces.IAuthSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthSpecificationImpl implements IAuthSpecification {

    @Override
    public Specification<EmpresaEntity> correo(String correo) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(correo, "null")) {
                return null;
            }
            return criteriaBuilder.equal(root.get("correoElectronico"), correo);
        });
    }

    @Override
    public Specification<EmpresaEntity> contrasena(String contrasena) {
        return ((root, query, criteriaBuilder) -> {
            if (Objects.equals(contrasena, "null")) {
                return null;
            }
            return criteriaBuilder.equal(root.get("contrasena"), contrasena);
        });
    }
}
