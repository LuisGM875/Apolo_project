package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.RecursoDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Repositories.IEmpresaRepository;
import com.aidr.backend.Repositories.IRecursoRepository;
import com.aidr.backend.Services.Interfaces.IRecursoService;
import com.aidr.backend.Specifications.Implements.RecursoSpecificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecursoServiceImpl implements IRecursoService {

    @Autowired
    private IRecursoRepository recursoRepository;
    @Autowired
    private IEmpresaRepository empresaRepository;
    @Autowired
    private RecursoSpecificationImpl recursoSpecification;

    @Override
    public RecursoDTO createRecurso(RecursoDTO recursoDTO) {
        RecursoEntity recurso = RecursoEntity.builder()
                .idRecurso(recursoDTO.getIdRecurso())
                .nombre(recursoDTO.getNombre())
                .primerApellido(recursoDTO.getPrimerApellido())
                .segundoApellido(recursoDTO.getSegundoApellido())
                .tecnologias(recursoDTO.getTecnologias())
                .archivo(recursoDTO.getArchivo())
                .descripcion(recursoDTO.getDescripcion())
                .tarifa(recursoDTO.getTarifa())
                .divisa(recursoDTO.getDivisa())
                .empresa(recursoDTO.getEmpresa())
                .estatus(recursoDTO.getEstatus())
                .modalidad(recursoDTO.getModalidad())
                .experiencia(recursoDTO.getExperiencia())
                .puesto(recursoDTO.getPuesto())
                .ocultar(recursoDTO.isOcultar())
                .fechaRegistro(LocalDate.now())
                .build();
        RecursoEntity recursoCreated = recursoRepository.save(recurso);
        recursoDTO.setIdRecurso(recursoCreated.getIdRecurso());
        EmpresaEntity empresaEntity = empresaRepository.findById(recursoCreated.getEmpresa().getIdEmpresa()).orElse(null);
        recursoDTO.setEmpresa(empresaEntity);
        return recursoDTO;
    }

    @Override
    public ResponseEntity<RecursoDTO> updateRecurso(Long idRecurso, RecursoDTO recursoDTO) {
        RecursoEntity recursoFound = recursoRepository.findById(idRecurso).orElse(null);
        assert recursoFound != null;
        recursoFound = RecursoEntity.builder()
                .idRecurso(recursoDTO.getIdRecurso())
                .nombre(recursoDTO.getNombre())
                .primerApellido(recursoDTO.getPrimerApellido())
                .segundoApellido(recursoDTO.getSegundoApellido())
                .tecnologias(recursoDTO.getTecnologias())
                .archivo(recursoDTO.getArchivo())
                .descripcion(recursoDTO.getDescripcion())
                .tarifa(recursoDTO.getTarifa())
                .divisa(recursoDTO.getDivisa())
                .empresa(recursoDTO.getEmpresa())
                .estatus(recursoDTO.getEstatus())
                .modalidad(recursoDTO.getModalidad())
                .experiencia(recursoDTO.getExperiencia())
                .puesto(recursoDTO.getPuesto())
                .ocultar(recursoDTO.isOcultar())
                .fechaRegistro(recursoFound.getFechaRegistro())
                .build();
        recursoRepository.save(recursoFound);
        return ResponseEntity.ok(recursoDTO);
    }

    @Override
    public List<RecursoDTO> findRecursos(Pageable pageable) {
        return recursoRepository.findAll(pageable).stream()
                .map(recurso -> new RecursoDTO(
                        recurso.getIdRecurso(),
                        recurso.getNombre(),
                        recurso.getPrimerApellido(),
                        recurso.getSegundoApellido(),
                        recurso.getTecnologias(),
                        recurso.getArchivo(),
                        recurso.getDescripcion(),
                        recurso.getTarifa(),
                        recurso.getDivisa(),
                        recurso.getEmpresa(),
                        recurso.getEstatus(),
                        recurso.getModalidad(),
                        recurso.getExperiencia(),
                        recurso.getPuesto(),
                        recurso.isOcultar()
                )).collect(Collectors.toList());
    }

    @Override
    public List<RecursoDTO> findRecursosMinEmpresa(Long idEmpresa, boolean ocultar) {
        Specification<RecursoEntity> spec = Specification.where(null);
        if (!Objects.equals(ocultar, true)) {
            spec = spec.and(recursoSpecification.empresaNotEqual(idEmpresa));
            spec = spec.and(recursoSpecification.ocultarNoTrue(ocultar));
            spec = spec.and(recursoSpecification.estatus());

            return recursoRepository.findAll(spec).stream()
                    .map(recurso -> new RecursoDTO(
                            recurso.getIdRecurso(),
                            recurso.getNombre(),
                            recurso.getPrimerApellido(),
                            recurso.getSegundoApellido(),
                            recurso.getTecnologias(),
                            recurso.getArchivo(),
                            recurso.getDescripcion(),
                            recurso.getTarifa(),
                            recurso.getDivisa(),
                            recurso.getEmpresa(),
                            recurso.getEstatus(),
                            recurso.getModalidad(),
                            recurso.getExperiencia(),
                            recurso.getPuesto(),
                            recurso.isOcultar()
                    )).collect(Collectors.toList());

        }
        return null;
    }

    @Override
    public List<RecursoDTO> findRecursosByFiltros(Long idEmpresa, Long idOcultarEmpresa, String puesto, String tecnologia, String modalidad, String experiencia, boolean ocultar, String min, String max) {
        Specification<RecursoEntity> spec = Specification.where(null);

        if (!Objects.equals(idEmpresa, 0L)) {
            spec = spec.and(recursoSpecification.empresa(idEmpresa));
        }

        if (!Objects.equals(idOcultarEmpresa, 0L)) {
            spec = spec.and(recursoSpecification.empresaNotEqual(idOcultarEmpresa));
        }

        if (!Objects.equals(puesto, "null")) {
            spec = spec.and(recursoSpecification.puesto(puesto));
        }
        if (!Objects.equals(modalidad, "null")) {
            spec = spec.and(recursoSpecification.modalidad(modalidad));
        }
        if (!Objects.equals(experiencia, "null")) {
            spec = spec.and(recursoSpecification.experiencia(experiencia));
        }
        if (!Objects.equals(min, max)) {
            spec = spec.and(recursoSpecification.tarifa(min, max));
        }
        if (!Objects.equals(tecnologia, "null")) {
            spec = spec.and(recursoSpecification.tecnologia(tecnologia));
        }

        spec = spec.and(recursoSpecification.ocultar(ocultar));

        return recursoRepository.findAll(spec).stream()
                .map(recurso -> new RecursoDTO(
                        recurso.getIdRecurso(),
                        recurso.getNombre(),
                        recurso.getPrimerApellido(),
                        recurso.getSegundoApellido(),
                        recurso.getTecnologias(),
                        recurso.getArchivo(),
                        recurso.getDescripcion(),
                        recurso.getTarifa(),
                        recurso.getDivisa(),
                        recurso.getEmpresa(),
                        recurso.getEstatus(),
                        recurso.getModalidad(),
                        recurso.getExperiencia(),
                        recurso.getPuesto(),
                        recurso.isOcultar()
                )).collect(Collectors.toList());
    }

    @Override
    public List<RecursoDTO> findRecursosByEmpresa(Long idEmpresa) {
        EmpresaEntity empresa = EmpresaEntity.builder()
                .idEmpresa(idEmpresa)
                .build();

        return recursoRepository.findAllByEmpresa(empresa).stream()
                .map(recurso -> new RecursoDTO(
                        recurso.getIdRecurso(),
                        recurso.getNombre(),
                        recurso.getPrimerApellido(),
                        recurso.getSegundoApellido(),
                        recurso.getTecnologias(),
                        recurso.getArchivo(),
                        recurso.getDescripcion(),
                        recurso.getTarifa(),
                        recurso.getDivisa(),
                        recurso.getEmpresa(),
                        recurso.getEstatus(),
                        recurso.getModalidad(),
                        recurso.getExperiencia(),
                        recurso.getPuesto(),
                        recurso.isOcultar()
                )).collect(Collectors.toList());
    }

    @Override
    public List<RecursoDTO> findRecursosByEmpresaOcultar(Long idEmpresa, boolean ocultar) {
        Specification<RecursoEntity> spec = Specification.where(null);

        if (!Objects.equals(idEmpresa, 0L)) {
            spec = spec.and(recursoSpecification.empresa(idEmpresa));
        }

        spec = spec.and(recursoSpecification.ocultar(ocultar));

        return recursoRepository.findAll(spec).stream()
                .map(recurso -> new RecursoDTO(
                        recurso.getIdRecurso(),
                        recurso.getNombre(),
                        recurso.getPrimerApellido(),
                        recurso.getSegundoApellido(),
                        recurso.getTecnologias(),
                        recurso.getArchivo(),
                        recurso.getDescripcion(),
                        recurso.getTarifa(),
                        recurso.getDivisa(),
                        recurso.getEmpresa(),
                        recurso.getEstatus(),
                        recurso.getModalidad(),
                        recurso.getExperiencia(),
                        recurso.getPuesto(),
                        recurso.isOcultar()
                )).collect(Collectors.toList());
    }

    @Override
    @Async
    public void deleteRecursoById(Long idRecurso) {
        recursoRepository.deleteById(idRecurso);
    }


    public ResponseEntity<RecursoDTO> updateEstatusRecurso(Long idRecurso, int estatus) {
        RecursoEntity recursoFound = recursoRepository.findById(idRecurso).orElse(null);

        if (recursoFound == null) {
            return ResponseEntity.notFound().build();
        }

        recursoFound.setEstatus(estatus);

        recursoRepository.save(recursoFound);

        RecursoDTO recursoDTO = new RecursoDTO(
                recursoFound.getIdRecurso(),
                recursoFound.getNombre(),
                recursoFound.getPrimerApellido(),
                recursoFound.getSegundoApellido(),
                recursoFound.getTecnologias(),
                recursoFound.getArchivo(),
                recursoFound.getDescripcion(),
                recursoFound.getTarifa(),
                recursoFound.getDivisa(),
                recursoFound.getEmpresa(),
                recursoFound.getEstatus(),
                recursoFound.getModalidad(),
                recursoFound.getExperiencia(),
                recursoFound.getPuesto(),
                recursoFound.isOcultar()
        );

        return ResponseEntity.ok(recursoDTO);
    }




}
