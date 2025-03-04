package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.EvaluacionDTO;
import com.aidr.backend.DTOs.RecursoDTO;
import com.aidr.backend.Models.EvaluacionEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Repositories.IEvaluacionRepository;
import com.aidr.backend.Repositories.IRecursoRepository;
import com.aidr.backend.Services.Interfaces.IEvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

    @Autowired
    IEvaluacionRepository evaluacionRepository;
    @Autowired
    IRecursoRepository recursoRepository;

    @Override
    public EvaluacionDTO createEvaluacion(Long idRecurso ,EvaluacionDTO evaluacionDTO) {
        RecursoEntity recursoFound = recursoRepository.findById(idRecurso).orElse(null);

        EvaluacionEntity evaluacion = EvaluacionEntity.builder()
                .recurso(evaluacionDTO.getRecurso())
                .estrella1(evaluacionDTO.getEstrella1())
                .estrella2(evaluacionDTO.getEstrella2())
                .estrella3(evaluacionDTO.getEstrella3())
                .estrella4(evaluacionDTO.getEstrella4())
                .estrella5(evaluacionDTO.getEstrella5())
                .totalEvaluaciones(evaluacionDTO.getTotalEvaluaciones())
                .promedio(evaluacionDTO.getPromedio())
                .estatus(evaluacionDTO.isEstatus())
                .build();

        evaluacionRepository.save(evaluacion);
        return evaluacionDTO;
    }

    @Override
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(EvaluacionDTO evaluacionDTO) {
        EvaluacionEntity evaluacionFound = evaluacionRepository.findByRecurso(evaluacionDTO.getRecurso());

        evaluacionDTO.setTotalEvaluaciones(evaluacionDTO.getEstrella1() + evaluacionDTO.getEstrella2() + evaluacionDTO.getEstrella3() + evaluacionDTO.getEstrella4() + evaluacionDTO.getEstrella5());
        int totalEvaluaciones = evaluacionDTO.getTotalEvaluaciones();
        evaluacionDTO.setPromedio((float) ((evaluacionDTO.getEstrella1()) + (evaluacionDTO.getEstrella2() * 2) + (evaluacionDTO.getEstrella3() * 3) + (evaluacionDTO.getEstrella4() * 4) + (evaluacionDTO.getEstrella5() * 5)) / totalEvaluaciones);

        evaluacionFound = EvaluacionEntity.builder()
                .idEvaluacion(evaluacionDTO.getIdEvaluacion())
                .estrella1(evaluacionDTO.getEstrella1())
                .estrella2(evaluacionDTO.getEstrella2())
                .estrella3(evaluacionDTO.getEstrella3())
                .estrella4(evaluacionDTO.getEstrella4())
                .estrella5(evaluacionDTO.getEstrella5())
                .totalEvaluaciones(evaluacionDTO.getTotalEvaluaciones())
                .recurso(evaluacionDTO.getRecurso())
                .promedio(evaluacionDTO.getPromedio())
                .estatus(evaluacionDTO.isEstatus())
                .build();
        evaluacionRepository.save(evaluacionFound);
        return ResponseEntity.ok(evaluacionDTO);
    }

    @Override
    public ResponseEntity<EvaluacionDTO> findEvaluacionByRecurso(Long idRecurso) {
        RecursoEntity recursoFound = recursoRepository.findById(idRecurso).orElse(null);
        EvaluacionEntity evaluacionFound = evaluacionRepository.findByRecurso(recursoFound);

        EvaluacionDTO evaluacionDTO = EvaluacionDTO.builder()
                .estrella1(evaluacionFound.getEstrella1())
                .estrella2(evaluacionFound.getEstrella2())
                .estrella3(evaluacionFound.getEstrella3())
                .estrella4(evaluacionFound.getEstrella4())
                .estrella5(evaluacionFound.getEstrella5())
                .totalEvaluaciones(evaluacionFound.getTotalEvaluaciones())
                .promedio(evaluacionFound.getPromedio())
                .estatus(evaluacionFound.isEstatus())
                .build();
        return ResponseEntity.ok(evaluacionDTO);
    }

    @Override
    public List<EvaluacionDTO> findEvaluaciones() {
        return evaluacionRepository.findAll().stream()
                .map(evaluacion -> new EvaluacionDTO(
                        evaluacion.getIdEvaluacion(),
                        evaluacion.getRecurso(),
                        evaluacion.getEstrella1(),
                        evaluacion.getEstrella2(),
                        evaluacion.getEstrella3(),
                        evaluacion.getEstrella4(),
                        evaluacion.getEstrella5(),
                        evaluacion.getPromedio(),
                        evaluacion.getTotalEvaluaciones(),
                        evaluacion.isEstatus()
                )).collect(Collectors.toList());
    }
}
