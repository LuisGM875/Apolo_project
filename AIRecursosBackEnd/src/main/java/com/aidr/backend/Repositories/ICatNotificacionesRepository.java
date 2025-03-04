package com.aidr.backend.Repositories;

import com.aidr.backend.Models.CatNotificacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatNotificacionesRepository extends JpaRepository<CatNotificacionesEntity, Long> {
}
