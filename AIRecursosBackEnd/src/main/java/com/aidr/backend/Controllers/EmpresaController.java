package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Services.Implements.EmpresaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/v1/e")
public class EmpresaController {

    @Autowired
    EmpresaServiceImpl empresaService;

    @PostMapping("/empresa")
    public EmpresaDTO createEmpresa(
            @RequestPart("empresa") String empresaDTOJson,
            @RequestPart("logo") byte[] logo,
            @RequestPart("pdfFile") byte[] pdfFile
    ) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmpresaDTO empresaDTO = objectMapper.readValue(empresaDTOJson, EmpresaDTO.class);
        return empresaService.createEmpresa(empresaDTO, pdfFile, logo);
    }

    @PutMapping("/empresa")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@RequestPart("empresa") String empresaDTOJson, @RequestPart("pdfFile") byte[] pdfFile ,@RequestPart("logo") byte[] logo) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmpresaDTO empresaDTO = objectMapper.readValue(empresaDTOJson, EmpresaDTO.class);
        return empresaService.updateEmpresa(empresaDTO , pdfFile ,logo);
    }

    @GetMapping("/empresas")
    public List<EmpresaDTO> findEmpresas() {
        return empresaService.findEmpresas();
    }
}
