package com.hiten.spring_basic_by_telusko.service;

import com.hiten.spring_basic_by_telusko.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Service
public class JwtService {

    private String secretKey="";

    public String generateToken(String userName) {

        Map<String,Object> claims= new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(getKey())
                .compact();


    }

    private Key getKey() {
        try {
            KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk=keyGen.generateKey();
            secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }
}
