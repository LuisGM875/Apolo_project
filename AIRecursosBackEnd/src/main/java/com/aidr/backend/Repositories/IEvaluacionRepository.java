package com.aidr.backend.Repositories;

import com.aidr.backend.Models.EvaluacionEntity;
import com.aidr.backend.Models.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEvaluacionRepository extends JpaRepository<EvaluacionEntity, Long> {
    public EvaluacionEntity findByRecurso(RecursoEntity recurso);
}
