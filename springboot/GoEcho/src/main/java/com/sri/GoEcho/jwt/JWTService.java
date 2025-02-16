package com.sri.GoEcho.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {



    private SecretKey finalSecretKey;

    public JWTService() {
        finalSecretKey = null;
    }


    public String generateToken(String username) {

Map<String,Object> claims=new HashMap<>();

return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+600000))
        .and()
        .signWith(generateKey())
        .compact();
    }

    private SecretKey  generateKey()  {
        KeyGenerator key= null;
        try {
            key = KeyGenerator.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        SecretKey sk= key.generateKey();


        String secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());



        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        finalSecretKey= Keys.hmacShaKeyFor(keyBytes);
        return finalSecretKey;

    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token,Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(finalSecretKey)
                .build()

                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);


        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
