package com.aidr.backend.Controllers;

import com.aidr.backend.DTOs.ArchivoDTO;
import com.aidr.backend.Services.Implements.ArchivoServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/a/recurso")
@AllArgsConstructor
public class ArchivoController {

    private static final Logger log = LoggerFactory.getLogger(ArchivoController.class);
    @Autowired
    ArchivoServiceImpl archivoService;

    @PostMapping("/archivo")
    public ArchivoDTO createArchivo(@RequestPart("file") MultipartFile file,
                                    @RequestParam(name = "nombre") String nombre,
                                    @RequestParam(name = "idEmpresa") Long idEmpresa) throws NoSuchPaddingException, IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return archivoService.createArchivo(file, nombre, idEmpresa);
    }

    @PutMapping("/archivo/{idArchivo}")
    public ResponseEntity<ArchivoDTO> updateArchivo(
            @PathVariable(name = "idArchivo") Long idArchivo,
            @RequestBody MultipartFile file,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "idEmpresa") Long idEmpresa) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return archivoService.updateArchivo(idArchivo, file, nombre, idEmpresa);
    }

    @GetMapping("/archivo/{idRecurso}")
    public ResponseEntity<byte[]> findArchivoByRecurso(@PathVariable(name = "idRecurso") Long idRecurso) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException {
        return archivoService.findArchivoByRecurso(idRecurso);
    }

    @DeleteMapping("/archivo/{idArchivo}")
    public void deleteArchivo(@PathVariable(name = "idArchivo") Long idArchivo) {
        archivoService.deleteArchivo(idArchivo);
    }
}
