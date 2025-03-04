package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.TecnologiaDTO;
import com.aidr.backend.Models.TecnologiaEntity;
import com.aidr.backend.Services.Implements.TecnologiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/t")
public class TecnologiaController {

    @Autowired
    TecnologiaServiceImpl tecnologiaService;

    @GetMapping("/tecnologias")
    public List<TecnologiaDTO> finTecnologias() {
        return tecnologiaService.findTecnologias();
    }

}
