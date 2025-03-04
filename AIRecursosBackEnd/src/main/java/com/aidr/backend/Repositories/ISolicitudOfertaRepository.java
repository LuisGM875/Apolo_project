package com.aidr.backend.Repositories;

import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.SolicitudOfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolicitudOfertaRepository extends JpaRepository<SolicitudOfertaEntity, Long>, JpaSpecificationExecutor<SolicitudOfertaEntity> {
}
