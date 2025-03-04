package com.aidr.backend.Services.Implements;

import com.aidr.backend.DTOs.EmpresaDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtUtilService {

    private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";
    private static final long JWT_TIME_VALIDITY = 1000 * 60 * 15;
    private static final long JWT_TIME_REFRESH_VALIDATE = 1000 * 60 * 60 * 24;

    public String generateToken(EmpresaDTO empresaDTO) {
        var claims = new HashMap<String, Object>();
        claims.put("correoElectronico", empresaDTO.getCorreoElectronico());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(empresaDTO.getCorreoElectronico())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

}

