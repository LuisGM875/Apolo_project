package com.aidr.backend.Repositories;

import com.aidr.backend.Models.NotificacionesEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionesEmpresaRepository extends JpaRepository<NotificacionesEmpresaEntity, Long> {
}
