package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.EvaluacionDTO;
import com.aidr.backend.Services.Implements.EvaluacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/e/recurso")
public class EvaluacionController {

    @Autowired
    EvaluacionServiceImpl evaluacionService;

    @PostMapping("/evaluacion/{idRecurso}")
    public EvaluacionDTO createEvaluacion(@PathVariable(name = "idRecurso") Long idRecurso, @RequestBody EvaluacionDTO evaluacionDTO) {
        return evaluacionService.createEvaluacion(idRecurso, evaluacionDTO);
    }

    @PutMapping("/evaluacion")
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(@RequestBody EvaluacionDTO evaluacionDTO) {
        return  evaluacionService.updateEvaluacion(evaluacionDTO);
    }

    @GetMapping("/evaluacion/{idRecurso}")
    public ResponseEntity<EvaluacionDTO> findEvaluacion(@PathVariable(name = "idRecurso") Long idRecurso) {
        return evaluacionService.findEvaluacionByRecurso(idRecurso);
    }

    @GetMapping("/evaluaciones")
    public List<EvaluacionDTO> findEvaluacion() {
        return evaluacionService.findEvaluaciones();
    }



}
