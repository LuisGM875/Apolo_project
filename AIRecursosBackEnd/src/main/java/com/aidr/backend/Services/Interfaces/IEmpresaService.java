package com.aidr.backend.Services.Interfaces;

import com.aidr.backend.DTOs.EmpresaDTO;
import com.aidr.backend.Models.EmpresaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IEmpresaService {

    public EmpresaDTO createEmpresa(EmpresaDTO empresaDTO, byte[] pdf, byte[] logo) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    public ResponseEntity<EmpresaDTO> updateEmpresa(EmpresaDTO empresaDTO, byte[] pdfFile,byte[] logo) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    public List<EmpresaDTO> findEmpresas();
    public String generatePasswordArchivo();
    public byte[] ecnodeArchivo(byte[] file, Long idEmpresa) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    public byte[] deEcnodeArchivo(byte[] ecnodeArchivo, Long idEmpresa) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException;

}
