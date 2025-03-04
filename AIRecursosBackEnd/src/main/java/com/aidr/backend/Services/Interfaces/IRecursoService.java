package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.RecursoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRecursoService {

    RecursoDTO createRecurso(RecursoDTO recursoDTO);

    ResponseEntity<RecursoDTO> updateRecurso(Long idRecurso, RecursoDTO recurso);

    List<RecursoDTO> findRecursos(Pageable pageable);

    List<RecursoDTO> findRecursosMinEmpresa(Long idEmpresa, boolean ocultar);

    List<RecursoDTO> findRecursosByFiltros(Long idEmpresa, Long idOcultarEmpresa,String puesto, String tecnologia, String modalidad, String experiencia, boolean ocultar , String min, String max);

    List<RecursoDTO> findRecursosByEmpresa(Long idEmpresa);

    List<RecursoDTO> findRecursosByEmpresaOcultar(Long idEmpresa, boolean ocultar);

    void deleteRecursoById(Long idRecurso);

}
