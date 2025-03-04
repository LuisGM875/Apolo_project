package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.ArchivoDTO;
import com.aidr.backend.Models.ArchivoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IArchivoService {
    public ArchivoDTO createArchivo(MultipartFile file, String nombre, Long idEmpresa) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    public ResponseEntity<ArchivoDTO> updateArchivo(Long idArchivo, MultipartFile file, String nombre, Long idEmpresa) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
    public ResponseEntity<byte[]> findArchivoByRecurso(Long idRecurso) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException;
    public void deleteArchivo(Long idArchivo);
    public byte[] ecnodeArchivo(MultipartFile file, Long idEmpresa) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    public byte[] deEcnodeArchivo(byte[] ecnodeArchivo, Long idEmpresa) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException;
}
