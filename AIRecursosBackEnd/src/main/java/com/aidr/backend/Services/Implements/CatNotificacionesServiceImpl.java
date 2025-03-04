package com.aidr.backend.Services.Implements;

import com.aidr.backend.Models.CatNotificacionesEntity;
import com.aidr.backend.Repositories.ICatNotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatNotificacionesServiceImpl {

  @Autowired
  ICatNotificacionesRepository ICatNotificacionesRepository;

  public List<CatNotificacionesEntity> getAllNotificaciones() {
    return ICatNotificacionesRepository.findAll();
  }

  public Optional<CatNotificacionesEntity> getNotificacionById(Long id) {
    return ICatNotificacionesRepository.findById(id);
  }

  public void saveOrUpdate(CatNotificacionesEntity notificacion) {
    ICatNotificacionesRepository.save(notificacion);
  }

  public void delete(Long id) {
    ICatNotificacionesRepository.deleteById(id);
  }
}
