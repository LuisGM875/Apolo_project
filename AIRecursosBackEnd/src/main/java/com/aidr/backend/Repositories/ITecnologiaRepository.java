package com.aidr.backend.Repositories;

import com.aidr.backend.Models.TecnologiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITecnologiaRepository extends JpaRepository<TecnologiaEntity, Long> {
}
