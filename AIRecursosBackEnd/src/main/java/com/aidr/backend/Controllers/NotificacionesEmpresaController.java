package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.NotificacionesXEmpresaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaXDemandaDTO;
import com.aidr.backend.Models.NotificacionesEmpresaEntity;
import com.aidr.backend.Services.Implements.NotificacionesXEmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notificaciones-empresa")
public class NotificacionesEmpresaController {

  @Autowired
  private NotificacionesXEmpresaServiceImpl notificacionesEmpresaServiceImpl;

  @GetMapping
  public List<NotificacionesXEmpresaDTO> getAll() {
    return notificacionesEmpresaServiceImpl.getAllNotificacionesEmpresa();
  }

  @GetMapping("/{idNotificacionXEmpresa}")
  public Optional<NotificacionesEmpresaEntity> getById(@PathVariable("idNotificacionXEmpresa") long idNotificacionXEmpresa) {
    return notificacionesEmpresaServiceImpl.getNotificacionEmpresaById(idNotificacionXEmpresa);
  }

  @PostMapping("/NotificacionXEmpresa")
  public NotificacionesXEmpresaDTO createnotificacionXempresa(@RequestBody NotificacionesXEmpresaDTO notificacionesXEmpresaDTO) {
    return notificacionesEmpresaServiceImpl.createNotificacionXEmpresa(notificacionesXEmpresaDTO);
  }


  @DeleteMapping("/{idNotificacionXEmpresa}")
  public void delete(@PathVariable("idNotificacionXEmpresa") long idNotificacionXEmpresa) {
    notificacionesEmpresaServiceImpl.delete(idNotificacionXEmpresa);
  }
  @PutMapping("/leido/{idNotificacionXEmpresa}")
  public void markAsRead(
          @PathVariable("idNotificacionXEmpresa") long idNotificacionXEmpresa) {
    notificacionesEmpresaServiceImpl.markAsRead(idNotificacionXEmpresa);
  }

  @PutMapping("/actualizarNotificacion/{idNotificacionXEmpresa}/{nuevoIdNotificacion}")
  public void actualizarNotificacion(
          @PathVariable("idNotificacionXEmpresa") long idNotificacionXEmpresa,
          @PathVariable("nuevoIdNotificacion") long nuevoIdNotificacion) {

    notificacionesEmpresaServiceImpl.actualizarNotificacion(idNotificacionXEmpresa, nuevoIdNotificacion);
  }




}
