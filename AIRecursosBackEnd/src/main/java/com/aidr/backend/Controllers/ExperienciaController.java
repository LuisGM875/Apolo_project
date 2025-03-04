package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.ExperienciaDTO;
import com.aidr.backend.Services.Implements.ExperienciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/e")
public class ExperienciaController {

    @Autowired
    ExperienciaServiceImpl experienciaService;

    @GetMapping("/experiencias")
    public List<ExperienciaDTO> experienciaService() {
        return experienciaService.findExperiencias();
    }
}
