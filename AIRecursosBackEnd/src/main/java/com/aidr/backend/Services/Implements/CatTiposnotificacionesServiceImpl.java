package com.aidr.backend.Services.Implements;

import com.aidr.backend.Models.CatTiposnotificacionesEntity;
import com.aidr.backend.Repositories.ICatTiposnotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatTiposnotificacionesServiceImpl {

  @Autowired
  ICatTiposnotificacionesRepository ICatTiposnotificacionesRepository;

  public List<CatTiposnotificacionesEntity> getAllTiposNotificaciones() {
    return ICatTiposnotificacionesRepository.findAll();
  }

  public Optional<CatTiposnotificacionesEntity> getTipoNotificacionById(Long id) {
    return ICatTiposnotificacionesRepository.findById(id);
  }

  public void saveOrUpdate(CatTiposnotificacionesEntity tipoNotificacion) {
    ICatTiposnotificacionesRepository.save(tipoNotificacion);
  }

  public void delete(Long id) {
    ICatTiposnotificacionesRepository.deleteById(id);
  }
}
