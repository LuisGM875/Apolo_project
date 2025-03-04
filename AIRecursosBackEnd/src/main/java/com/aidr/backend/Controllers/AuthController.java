package com.aidr.backend.Controllers;


import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.Services.Implements.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @GetMapping ("/login")
    public List<EmpresaDTO> login(@RequestParam(name = "correo") String correo, @RequestParam(name = "contrasena") String contrasena) {
        return authService.login(correo, contrasena);

    }
}
