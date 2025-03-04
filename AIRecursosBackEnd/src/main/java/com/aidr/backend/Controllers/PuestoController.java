package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.PuestoDTO;
import com.aidr.backend.Services.Implements.PuestoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/p")
public class PuestoController {

    @Autowired
    PuestoServiceImpl puestoService;

    @GetMapping("/puestos")
    public List<PuestoDTO> findPuestos() {
        return puestoService.findPuestos();
    }
}
