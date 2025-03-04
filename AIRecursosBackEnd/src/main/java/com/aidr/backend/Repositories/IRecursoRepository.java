package com.aidr.backend.Repositories;

import com.aidr.backend.DTOs.RecursoDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecursoRepository extends JpaRepository<RecursoEntity, Long>, JpaSpecificationExecutor<RecursoEntity> {
    List<RecursoEntity> findAllByEmpresa(EmpresaEntity empresa);
}
