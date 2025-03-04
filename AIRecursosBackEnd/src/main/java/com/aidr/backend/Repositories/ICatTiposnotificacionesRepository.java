package com.aidr.backend.Repositories;

import com.aidr.backend.Models.CatTiposnotificacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatTiposnotificacionesRepository extends JpaRepository<CatTiposnotificacionesEntity, Long> {
}
