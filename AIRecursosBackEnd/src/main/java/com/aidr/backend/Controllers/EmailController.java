package com.aidr.backend.Controllers;

import com.aidr.backend.Services.Implements.EmailService;
import com.aidr.backend.Services.Implements.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1")
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    private EmpresaServiceImpl empresaService;



@GetMapping("/email/send-token")
public ResponseEntity<?> sendTokenEmail(@RequestParam ("correo") String correoElectronico) {
    try {
        empresaService.sendTokenToRegisteredCompany(correoElectronico);
        return null;
    } catch (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam String jwt) {
        boolean isValid = empresaService.validateTokenByToken(jwt);
        if (isValid) {
            return null;
        } else {
            return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam("jwt") String jwt,
            @RequestParam("newPassword") String newPassword
    ) {
        try {
            empresaService.resetPassword(jwt, newPassword);
//            return ResponseEntity.ok("Contraseña actualizada exitosamente");
            return null;
        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
