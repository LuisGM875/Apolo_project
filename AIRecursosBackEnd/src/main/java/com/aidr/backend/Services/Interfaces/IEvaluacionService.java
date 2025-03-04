package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.EvaluacionDTO;
import com.aidr.backend.DTOs.RecursoDTO;
import com.aidr.backend.Models.EvaluacionEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEvaluacionService {
    public EvaluacionDTO createEvaluacion(Long idRecurso ,EvaluacionDTO evaluacionDTO);
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(EvaluacionDTO evaluacionDTO);
    public ResponseEntity<EvaluacionDTO> findEvaluacionByRecurso(Long idRecurso);
    public List<EvaluacionDTO> findEvaluaciones();

}
