package com.aidr.backend.Repositories;

import com.aidr.backend.Models.PuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuestoRepository extends JpaRepository<PuestoEntity, Long> {
}
