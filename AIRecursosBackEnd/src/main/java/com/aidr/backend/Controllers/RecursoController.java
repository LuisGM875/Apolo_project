package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.DTOs.RecursoDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Services.Implements.RecursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/v1/r")
public class RecursoController {

    @Autowired
    RecursoServiceImpl recursoService;

    @PostMapping("/recurso") /*METODO PARA CREAR RECURSOS*/
    public RecursoDTO createRecurso(@RequestBody RecursoDTO recursoDTO) {
        return recursoService.createRecurso(recursoDTO);
    }

    @PutMapping("/recurso/{idRecurso}") /*METODO PARA ACTUALIZAR RECURSOS*/
    public ResponseEntity<RecursoDTO> updateRecurso(@PathVariable(name = "idRecurso") Long idRecurso, @RequestBody RecursoDTO recursoDTO) {
        return recursoService.updateRecurso(idRecurso, recursoDTO);
    }

    /*@GetMapping("/recursos") METODO PARA OBTENER TODOS LOS RECURSOS
    public List<RecursoDTO> findRecursos(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return recursoService.findRecursos(pageable);
    }*/

    @GetMapping("/recursos/empresa/{idEmpresa}") /*METODO PARA OBTENER TODOS LOS RECURSOS DE UNA EMPRESA*/
    public List<RecursoDTO> findRecursosByEmpresa(@PathVariable(name = "idEmpresa") Long idEmpresa) {
        return recursoService.findRecursosByEmpresa(idEmpresa);
    }

    // SIRVE
    @GetMapping("/recursos") /*METODO QUE OBTIENE LOS RECURSOS DE TODAS LAS EMPRESAS MENOS DE UNA EMPRESA ESPECIFICA*/
    public List<RecursoDTO> findRecursosMinEmpresa(@RequestParam(name = "idEmpresa") Long idEmpresa, @RequestParam(name = "ocultar") boolean ocultar) {
        return recursoService.findRecursosMinEmpresa(idEmpresa, ocultar);
    }

    //SIRVE
    @GetMapping("/recursos/empresa") /*METODO PARA OBTENER LOS RECURSOS OCULTOS O NO OCULTOS DE UNA EMPRESA SEGUN SEA EL VALOR DE OCULTAR*/
    public List<RecursoDTO> findRecursosByOcultar(@RequestParam(name = "idEmpresa") Long idEmpresa, @RequestParam(name = "ocultar") boolean ocultar) {
        return recursoService.findRecursosByEmpresaOcultar(idEmpresa, ocultar);
    }

    @GetMapping("/recursos/filtros") /*METODO PARA OBTENER RECURSOS POR FILTROS*/
    public List<RecursoDTO> findRecursosByFiltros(@RequestParam("idEmpresa") Long idEmpresa, @RequestParam("idOcultarEmpresa") Long idOcultarEmpresa, @RequestParam("puesto") String puesto, @RequestParam("tecnologia") String tecnologia , @RequestParam("modalidad") String modalidad, @RequestParam("experiencia") String experiencia, @RequestParam("ocultar") boolean ocultar, @RequestParam("min") String min, @RequestParam("max") String max) {
        return recursoService.findRecursosByFiltros(idEmpresa, idOcultarEmpresa, puesto, tecnologia, modalidad, experiencia, ocultar, min, max);
    }

    @DeleteMapping("/recurso/{idRecurso}") /*METODO PARA ELIMINAR FUERTEMENTE RECURSOS POR ID*/
    public void deleteRecursoById(@PathVariable(name = "idRecurso") Long idRecurso) {
        recursoService.deleteRecursoById(idRecurso);
    }

    @PutMapping("/{idRecurso}/estatus") /*METODO PARA CAMBIAR ESTATUS*/
    public ResponseEntity<RecursoDTO> updateEstatusRecurso(
            @PathVariable("idRecurso") Long idRecurso,
            @RequestParam("estatus") int estatus) {

        return recursoService.updateEstatusRecurso(idRecurso, estatus);
    }

}
