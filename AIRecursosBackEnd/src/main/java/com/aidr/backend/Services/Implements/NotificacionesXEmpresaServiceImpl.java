package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.NotificacionesXEmpresaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaXDemandaDTO;
import com.aidr.backend.Models.*;
import com.aidr.backend.Repositories.ICatNotificacionesRepository;
import com.aidr.backend.Repositories.INotificacionesEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificacionesXEmpresaServiceImpl {

    @Autowired
    private INotificacionesEmpresaRepository INotificacionesEmpresaRepository;

    @Autowired
    private ICatNotificacionesRepository ICatNotificacionesRepository;


    public List<NotificacionesXEmpresaDTO> getAllNotificacionesEmpresa() {
        return INotificacionesEmpresaRepository.findAll().stream()
                .map(notificaciones -> new NotificacionesXEmpresaDTO(
                        notificaciones.getIdNotificacionXEmpresa(),
                        notificaciones.getNotificacion(),
                        notificaciones.getEmpresa(),
                        notificaciones.getRecurso(),
                        notificaciones.getSolicitudesOfertaXDemanda(),
                        notificaciones.isLeido()
                )).collect(Collectors.toList());
    }

    public NotificacionesXEmpresaDTO createNotificacionXEmpresa(NotificacionesXEmpresaDTO notificacionesXEmpresaDTO) {
        SolicitudesOfertaXDemanda solicitudOfertaXDemanda = notificacionesXEmpresaDTO.getSolicitudesOfertaXDemanda();
        CatNotificacionesEntity notificacion = notificacionesXEmpresaDTO.getNotificacion();
        EmpresaEntity empresa = notificacionesXEmpresaDTO.getEmpresa();
        RecursoEntity recurso = notificacionesXEmpresaDTO.getRecurso();
        boolean leido = notificacionesXEmpresaDTO.isLeido();

        NotificacionesEmpresaEntity notificacionesXEmpresa = NotificacionesEmpresaEntity.builder()
                .empresa(empresa)
                .recurso(recurso)
                .solicitudesOfertaXDemanda(solicitudOfertaXDemanda)
                .notificacion(notificacion)
                .leido(leido)
                .build();

        NotificacionesEmpresaEntity savedNotificacionesXEmpresa = INotificacionesEmpresaRepository.save(notificacionesXEmpresa);

        notificacionesXEmpresaDTO.setIdNotificacionXEmpresa(savedNotificacionesXEmpresa.getIdNotificacionXEmpresa());

        return notificacionesXEmpresaDTO;
    }



    public Optional<NotificacionesEmpresaEntity> getNotificacionEmpresaById(Long id) {
        return INotificacionesEmpresaRepository.findById(id);
    }


    public void saveOrUpdate(NotificacionesEmpresaEntity notificacionEmpresa) {
        INotificacionesEmpresaRepository.save(notificacionEmpresa);
    }


    public void delete(Long id) {
        if (INotificacionesEmpresaRepository.existsById(id)) {
            INotificacionesEmpresaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notificación no encontrada con ID: " + id);
        }
    }


    public void markAsRead(long idNotificacionXEmpresa) {
        Optional<NotificacionesEmpresaEntity> notificacionOpt = INotificacionesEmpresaRepository.findById(idNotificacionXEmpresa);
        if (notificacionOpt.isPresent()) {
            NotificacionesEmpresaEntity notificacion = notificacionOpt.get();
            notificacion.setLeido(true);
            INotificacionesEmpresaRepository.save(notificacion);
        } else {
            throw new RuntimeException("Notificación no encontrada con ID: " + idNotificacionXEmpresa);
        }
    }

    public void actualizarNotificacion(long idNotificacionXEmpresa, long nuevoIdNotificacion) {
        Optional<NotificacionesEmpresaEntity> notificacionOpt = INotificacionesEmpresaRepository.findById(idNotificacionXEmpresa);

        if (notificacionOpt.isPresent()) {
            NotificacionesEmpresaEntity notificacion = notificacionOpt.get();

            Optional<CatNotificacionesEntity> nuevaNotificacionOpt = ICatNotificacionesRepository.findById(nuevoIdNotificacion);

            if (nuevaNotificacionOpt.isPresent()) {
                CatNotificacionesEntity nuevaNotificacion = nuevaNotificacionOpt.get();
                notificacion.setNotificacion(nuevaNotificacion);

                notificacion.setLeido(false);

                INotificacionesEmpresaRepository.save(notificacion);
            } else {
                throw new RuntimeException("Notificación no encontrada con ID: " + nuevoIdNotificacion);
            }
        } else {
            throw new RuntimeException("NotificaciónXEmpresa no encontrada con ID: " + idNotificacionXEmpresa);
        }
    }


}
