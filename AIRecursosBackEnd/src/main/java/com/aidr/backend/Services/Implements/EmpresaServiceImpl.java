package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Repositories.IEmpresaRepository;
import com.aidr.backend.Services.Interfaces.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO, byte[] pdfFile, byte[] logo) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String passwordArchivo = generatePasswordArchivo();
        String correoNormalizado = empresaDTO.getCorreoElectronico().trim().replaceAll("\\s+", " ").toLowerCase();
        String nombreNormalizado = empresaDTO.getNombre().trim().replaceAll("\\s+", " ").toLowerCase();
        if (empresaRepository.existsByCorreoIgnoreCase(correoNormalizado)) {
            throw new RuntimeException("El correo electrónico ingresado ya está registrado.");
        }
        if (empresaRepository.existsByNombreIgnoreCase(nombreNormalizado)) {
            throw new RemoteException("El nombre ingresado ya está registrado.");
        }
        //byte[] logoBytes = logo.getBytes();
        String jwt = this.jwtUtilService.generateToken(empresaDTO);
        EmpresaEntity empresaEntity = EmpresaEntity.builder()
                .logo(logo)
                .nombre(empresaDTO.getNombre())
                .numeroContacto(empresaDTO.getNumeroContacto())
                .correoElectronico(empresaDTO.getCorreoElectronico())
                .domicilio(empresaDTO.getDomicilio())
                .contrasena(empresaDTO.getContrasena())
                .razonSocial(empresaDTO.getRazonSocial())
                .rfc(empresaDTO.getRfc())
                .repseFolio(empresaDTO.getRepseFolio())
                .estatus(true)
                .contrasenaArchivo(passwordArchivo)
                .fechaRegistro(LocalDate.now())
                .jwt(jwt)
                .build();

        empresaDTO.setContrasenaArchivo(passwordArchivo);
        EmpresaEntity empresa = empresaRepository.save(empresaEntity);
        byte[] encArchivo = ecnodeArchivo(pdfFile, empresa.getIdEmpresa());
        empresa.setRepsePDF(encArchivo);
        empresaDTO.setRepsePDF(encArchivo);
        empresaDTO.setLogo(logo);
        empresaDTO.setCorreoElectronico(empresaDTO.getCorreoElectronico().trim());
        empresaRepository.save(empresa);
        return empresaDTO;
    }


    @Override
    public ResponseEntity<EmpresaDTO> updateEmpresa(EmpresaDTO empresaDTO, byte[] pdfFile, byte[] logo ) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        EmpresaEntity empresaFound = empresaRepository.findById(empresaDTO.getIdEmpresa()).orElse(null);
        //byte[] logoByte = logo.getBytes();
        System.out.println("" + empresaFound.getFechaRegistro());
        EmpresaEntity empresaEntity = EmpresaEntity.builder()
                .idEmpresa(empresaDTO.getIdEmpresa())
                .nombre(empresaDTO.getNombre())
                .correoElectronico(empresaDTO.getCorreoElectronico())
                .contrasena(empresaDTO.getContrasena())
                .razonSocial(empresaDTO.getRazonSocial())
                .domicilio(empresaDTO.getDomicilio())
                .repseFolio(empresaDTO.getRepseFolio())
                .numeroContacto(empresaDTO.getNumeroContacto())
                .contrasenaArchivo(empresaDTO.getContrasenaArchivo())
                .logo(logo)
                .rfc(empresaDTO.getRfc())
                .fechaRegistro(empresaFound.getFechaRegistro())
                .jwt(empresaFound.getJwt())
                .estatus(true)
                .build();
        byte[] encArchivo = ecnodeArchivo(pdfFile, empresaDTO.getIdEmpresa());
        empresaEntity.setRepsePDF(encArchivo);
        empresaDTO.setRepsePDF(encArchivo);
        empresaDTO.setLogo(logo);
        empresaRepository.save(empresaEntity);
        return ResponseEntity.ok(empresaDTO);
    }

    @Override
    public List<EmpresaDTO> findEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresa -> new EmpresaDTO(
                        empresa.getIdEmpresa(),
                        empresa.getNombre(),
                        empresa.getContrasena(),
                        empresa.getCorreoElectronico(),
                        empresa.getRfc(),
                        empresa.getRazonSocial(),
                        empresa.getDomicilio(),
                        empresa.getRepseFolio(),
                        empresa.getRepsePDF(),
                        empresa.getNumeroContacto(),
                        empresa.getContrasenaArchivo(),
                        Base64.getEncoder().encodeToString(empresa.getLogo()).getBytes(),
                        empresa.getFechaRegistro(),
                        empresa.isEstatus(),
                        empresa.getJwt()
                )).collect(Collectors.toList());
    }

    @Override
    public String generatePasswordArchivo() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public void sendTokenToRegisteredCompany(String correoElectronico) {
        EmpresaEntity empresa = empresaRepository.findByCorreoElectronico(correoElectronico);
        if (empresa != null) {
            String token = empresa.getJwt();

            if (token != null && !token.isEmpty()) {
                emailService.sendEmailTemplate(correoElectronico, token, empresa.getNombre());
            } else {
                throw new RuntimeException("El token no está disponible para esta empresa.");
            }
        } else {
            throw new RuntimeException("LLa empresa no está registrada con el correo proporcionado.");
        }
    }

    public boolean validateTokenByToken(String jwt) {
        // Busca la empresa por el token
        EmpresaEntity empresa = empresaRepository.findByJwt(jwt);
        return empresa != null; // Retorna true si la empresa existe y el token es válido
    }

    public void resetPassword(String jwt, String newPassword) {
        EmpresaEntity empresa = empresaRepository.findByJwt(jwt);
        if (empresa == null) {
            throw new RuntimeException("Token inválido");
        }

        empresa.setContrasena(newPassword);

        EmpresaDTO empresaDTO = new EmpresaDTO(
                empresa.getIdEmpresa(),
                empresa.getNombre(),
                newPassword,
                empresa.getCorreoElectronico(),
                empresa.getRfc(),
                empresa.getRazonSocial(),
                empresa.getDomicilio(),
                empresa.getRepseFolio(),
                empresa.getRepsePDF(),
                empresa.getNumeroContacto(),
                empresa.getContrasenaArchivo(),
                empresa.getLogo(),
                empresa.getFechaRegistro(),
                empresa.isEstatus(),
                empresa.getJwt()
        );

        String newToken = jwtUtilService.generateToken(empresaDTO);
        empresa.setJwt(newToken);

        empresaRepository.save(empresa);
    }

    @Override
    public byte[] ecnodeArchivo(byte[] file, Long idEmpresa) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        EmpresaEntity empresaEntity = empresaRepository.findById(idEmpresa).orElse(null);
        String secretKey = empresaEntity.getContrasenaArchivo();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        //byte[] bytes = file.getBytes();
        byte[] buf = cipher.doFinal(file);
        return Base64.getEncoder().encode(buf);
    }

    @Override
    public byte[] deEcnodeArchivo(byte[] ecnodeArchivo, Long idEmpresa) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        EmpresaEntity empresaEntity = empresaRepository.findById(idEmpresa).orElse(null);
        String secretKey = empresaEntity.getContrasenaArchivo();
        byte[] message = Base64.getDecoder().decode(ecnodeArchivo);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        Cipher decipher = Cipher.getInstance("DESede");
        decipher.init(Cipher.DECRYPT_MODE, key);

        return decipher.doFinal(message);
    }
}
