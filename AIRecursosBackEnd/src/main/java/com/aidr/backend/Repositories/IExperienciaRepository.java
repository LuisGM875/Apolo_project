package com.aidr.backend.Repositories;

import com.aidr.backend.Models.ExperienciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<ExperienciaEntity, Long> {
}
