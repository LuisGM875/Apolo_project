package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.DTOs.SolicitudDemandaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaDTO;

import java.util.List;

public interface ISolicitudDemandaService {

    public SolicitudDemandaDTO createSolicitudDemanda(SolicitudDemandaDTO solicitudDemanda);
    public List<SolicitudDemandaDTO> findSolicitudesDemandaByEmpresa(Long idEmpresa, boolean ocultar);
    List<SolicitudDemandaDTO> findSolicitudesDemandaByFiltros(Long idEmpresa, String puesto, String tecnologia, String modalidad, String experiencia, String min, String max);
}
