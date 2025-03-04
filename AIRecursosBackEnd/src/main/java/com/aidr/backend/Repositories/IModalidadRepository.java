package com.aidr.backend.Repositories;

import com.aidr.backend.Models.ModalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModalidadRepository extends JpaRepository<ModalidadEntity, Long> {
}
