package com.aidr.backend.Repositories;

import com.aidr.backend.Models.EmpresaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpresaRepository extends JpaRepository<EmpresaEntity, Long>, JpaSpecificationExecutor<EmpresaEntity> {
    @Query(value = "SELECT COUNT (c) > 0 FROM EmpresaEntity c WHERE REPLACE (LOWER (c.correoElectronico), ' ', '') = REPLACE(LOWER (:correoElectronico), ' ', '')")
    boolean existsByCorreoIgnoreCase (@Param("correoElectronico") String correoElectronico);

    @Query(value = "SELECT COUNT (c) > 0 FROM EmpresaEntity c WHERE REPLACE (LOWER (c.nombre), ' ', '') = REPLACE(LOWER (:nombre), ' ', '')")
    boolean existsByNombreIgnoreCase (@Param("nombre") String nombre);

//    Optional<EmpresaEntity> findByCorreoElectronico(String correoElectronico);
    EmpresaEntity findByCorreoElectronico(String correoElectronico);
    EmpresaEntity findByJwt(String jwt);
}
