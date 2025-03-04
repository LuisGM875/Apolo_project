package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.ArchivoDTO;
import com.aidr.backend.Models.ArchivoEntity;
import com.aidr.backend.Models.EmpresaEntity;
import com.aidr.backend.Models.RecursoEntity;
import com.aidr.backend.Repositories.IArchivoRepository;
import com.aidr.backend.Repositories.IEmpresaRepository;
import com.aidr.backend.Repositories.IRecursoRepository;
import com.aidr.backend.Services.Interfaces.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;


@Service
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    IArchivoRepository archivoRepository;
    @Autowired
    IRecursoRepository recursoRepository;
    @Autowired
    IEmpresaRepository empresaRepository;

    @Override
    public ArchivoDTO createArchivo(MultipartFile file, String nombre, Long idEmpresa) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        /*if (Objects.equals(file.getContentType(), "application/pdf")) {
            byte[] encArchivo = ecnodeArchivo(file,idEmpresa);
            ArchivoDTO archivoDTO = ArchivoDTO.builder()
                    .nombre("CV_" + nombre)
                    .archivo(encArchivo)
                    .estatus(true)
                    .build();
            return ResponseEntity.ok(archivoDTO);
        }
        return null;*/

        byte[] encArchivo = ecnodeArchivo(file,idEmpresa);
        ArchivoDTO archivoDTO = ArchivoDTO.builder()
                .nombre("CV_" + nombre)
                .archivo(encArchivo)
                .estatus(true)
                .build();
        ArchivoEntity archivoEntity = ArchivoEntity.builder()
                .nombre("CV_" + nombre)
                .archivo(encArchivo)
                .estatus(true)
                .build();
        archivoRepository.save(archivoEntity);
        return archivoDTO;

    }

    @Override
    public ResponseEntity<ArchivoDTO> updateArchivo(Long idArchivo, MultipartFile file, String nombre, Long idEmpresa) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (!Objects.equals(file, null)) {
           /* if (Objects.equals(file.getContentType(), "application/pdf")) {
                ArchivoEntity archivoFound = archivoRepository.findById(idArchivo).orElse(null);
                byte[] encArchivo = ecnodeArchivo(file, idEmpresa);
                ArchivoEntity archivo = ArchivoEntity.builder()
                        .idArchivo(idArchivo)
                        .nombre("CV_" + nombre)
                        .archivo(encArchivo)
                        .build();
                ArchivoEntity archivoUpdated = archivoRepository.save(archivo);
                ArchivoDTO archivoDTO = ArchivoDTO.builder()
                        .idArchivo(archivoUpdated.getIdArchivo())
                        .nombre("CV_" + archivoUpdated.getNombre())
                        .archivo(encArchivo)
                        .build();
                return ResponseEntity.ok(archivoDTO);
            }*/
            ArchivoEntity archivoFound = archivoRepository.findById(idArchivo).orElse(null);
            byte[] encArchivo = ecnodeArchivo(file, idEmpresa);
            ArchivoEntity archivo = ArchivoEntity.builder()
                    .idArchivo(idArchivo)
                    .nombre("CV_" + nombre)
                    .archivo(encArchivo)
                    .build();
            ArchivoEntity archivoUpdated = archivoRepository.save(archivo);
            ArchivoDTO archivoDTO = ArchivoDTO.builder()
                    .idArchivo(archivoUpdated.getIdArchivo())
                    .nombre("CV_" + archivoUpdated.getNombre())
                    .archivo(encArchivo)
                    .build();
            return ResponseEntity.ok(archivoDTO);
        } else {
            ArchivoEntity archivoFound = archivoRepository.findById(idArchivo).orElse(null);
            assert archivoFound != null;
            ArchivoEntity archivo = ArchivoEntity.builder()
                    .idArchivo(idArchivo)
                    .nombre("CV_" + nombre)
                    .archivo(archivoFound.getArchivo())
                    .build();
            ArchivoEntity archivoUpdated = archivoRepository.save(archivo);
            ArchivoDTO archivoDTO = ArchivoDTO.builder()
                    .idArchivo(archivoUpdated.getIdArchivo())
                    .nombre("CV_" + archivoUpdated.getNombre())
                    .archivo(archivoFound.getArchivo())
                    .build();
            return ResponseEntity.ok(archivoDTO);
        }
    }

    @Override
    public ResponseEntity<byte[]> findArchivoByRecurso(Long idRecurso) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        RecursoEntity recurso = recursoRepository.findById(idRecurso).orElse(null);
        assert recurso != null;
        byte[] byteArchivo = recurso.getArchivo().getArchivo();
        byte[] archivo = deEcnodeArchivo(byteArchivo, recurso.getEmpresa().getIdEmpresa());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename(recurso.getArchivo().getNombre()).build());
        return ResponseEntity.ok().headers(headers).body(archivo);
    }

    @Override
    public void deleteArchivo(Long idArchivo) {
        archivoRepository.deleteById(idArchivo);
    }

    @Override
    public byte[] ecnodeArchivo(MultipartFile file, Long idEmpresa) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        EmpresaEntity empresaEntity = empresaRepository.findById(idEmpresa).orElse(null);
        String secretKey = empresaEntity.getContrasenaArchivo();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainTextBytes = file.getBytes();
        byte[] buf = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encode(buf);
    }

    @Override
    public byte[] deEcnodeArchivo(byte[] ecnodeArchivo, Long idEmpresa) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
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
