package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.ModalidadDTO;
import com.aidr.backend.Services.Implements.ModalidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/m")
public class ModalidadController {

    @Autowired
    ModalidadServiceImpl modalidadService;

    @GetMapping("/modalidades")
    public List<ModalidadDTO> findModalidades() {
        return modalidadService.findModalidades();
    }
}
