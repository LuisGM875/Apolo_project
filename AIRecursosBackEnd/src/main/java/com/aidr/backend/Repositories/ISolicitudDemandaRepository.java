package com.aidr.backend.Repositories;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Models.SolicitudDemandaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolicitudDemandaRepository extends JpaRepository<SolicitudDemandaEntity, Long>, JpaSpecificationExecutor<SolicitudDemandaEntity> {

}
