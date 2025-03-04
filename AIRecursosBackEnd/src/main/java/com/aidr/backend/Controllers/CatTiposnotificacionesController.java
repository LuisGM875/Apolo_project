package com.aidr.backend.Controllers;


import com.aidr.backend.Models.CatTiposnotificacionesEntity;
import com.aidr.backend.Services.Implements.CatTiposnotificacionesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tipos-notificaciones")
public class CatTiposnotificacionesController {

  @Autowired
  private CatTiposnotificacionesServiceImpl catTiposnotificacionesServiceImpl;

  @GetMapping
  public List<CatTiposnotificacionesEntity> getAll() {
    return catTiposnotificacionesServiceImpl.getAllTiposNotificaciones();
  }

  @GetMapping("/{idTipoNotificacion}")
  public Optional<CatTiposnotificacionesEntity> getById(@PathVariable("idTipoNotificacion") long idTipoNotificacion) {
    return catTiposnotificacionesServiceImpl.getTipoNotificacionById(idTipoNotificacion);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public CatTiposnotificacionesEntity saveUpdate(@RequestBody CatTiposnotificacionesEntity tipoNotificacion) {
    catTiposnotificacionesServiceImpl.saveOrUpdate(tipoNotificacion);
    return tipoNotificacion;
  }

  @DeleteMapping("/{idTipoNotificacion}")
  public void delete(@PathVariable("idTipoNotificacion") long idTipoNotificacion) {
    catTiposnotificacionesServiceImpl.delete(idTipoNotificacion);
  }
}

