package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Repositories.IEmpresaRepository;
import com.aidr.backend.Services.Interfaces.IAuthService;
import com.aidr.backend.Specifications.Implements.AuthSpecificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthSpecificationImpl authSpecification;
    @Autowired
    private IEmpresaRepository empresaRepository;

    @Override
    public List<EmpresaDTO> login(String correo, String contrasena) {
        Specification<EmpresaEntity> spec = Specification.where(null);
        spec = spec.and(authSpecification.correo(correo));
        spec = spec.and(authSpecification.contrasena(contrasena));
        return empresaRepository.findAll(spec).stream()
                .map(empresaEntity -> new EmpresaDTO(
                        empresaEntity.getIdEmpresa(),
                        empresaEntity.getNombre(),
                        empresaEntity.getContrasena(),
                        empresaEntity.getCorreoElectronico(),
                        empresaEntity.getRfc(),
                        empresaEntity.getRazonSocial(),
                        empresaEntity.getDomicilio(),
                        empresaEntity.getRepseFolio(),
                        empresaEntity.getRepsePDF(),
                        empresaEntity.getNumeroContacto(),
                        empresaEntity.getContrasenaArchivo(),
                        empresaEntity.getLogo(),
                        empresaEntity.getFechaRegistro(),
                        empresaEntity.isEstatus(),
                        empresaEntity.getJwt()
                )).collect(Collectors.toList());

    }
}
