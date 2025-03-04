package com.aidr.backend.Controllers;

import com.aidr.backend.Models.CatNotificacionesEntity;
import com.aidr.backend.Services.Implements.CatNotificacionesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Catnotificaciones")
public class CatNotificacionesController {

  @Autowired
  CatNotificacionesServiceImpl catNotificacionesServiceImpl;

  @GetMapping
  public List<CatNotificacionesEntity> getAll() {
    return catNotificacionesServiceImpl.getAllNotificaciones();
  }

  @GetMapping("/{idNotificacion}")
  public Optional<CatNotificacionesEntity> getById(@PathVariable("idNotificacion") long idNotificacion) {
    return catNotificacionesServiceImpl.getNotificacionById(idNotificacion);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public CatNotificacionesEntity saveUpdate(@RequestBody CatNotificacionesEntity notificacion) {
    catNotificacionesServiceImpl.saveOrUpdate(notificacion);
    return notificacion;
  }

  @DeleteMapping("/{idNotificacion}")
  public void delete(@PathVariable("idNotificacion") long idNotificacion) {
    catNotificacionesServiceImpl.delete(idNotificacion);
  }
}
