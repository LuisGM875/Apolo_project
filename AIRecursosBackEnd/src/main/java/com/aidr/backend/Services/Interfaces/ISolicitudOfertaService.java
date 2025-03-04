package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.DTOs.SolicitudOfertaDTO;

import java.util.List;

public interface ISolicitudOfertaService {
    SolicitudOfertaDTO createSolicitudOferta(SolicitudOfertaDTO solicitudOfertaDTO);
    List<SolicitudOfertaDTO> findSolicitudesOferta();
    List<SolicitudOfertaDTO> findSolicitudesOfertaByEmpresa(Long idEmpresa, boolean estatus);
    List<SolicitudOfertaDTO> findSolicitudesOfertaByFiltros(Long idEmpresa, String puesto, String tecnologia, String modalidad, String experiencia, String min, String max);
}
