package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.EmpresaDTO;

import java.util.List;

public interface IAuthService {
    List<EmpresaDTO> login(String correo, String contrasena);
}
