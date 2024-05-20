package com.anil.SpringSecurityJWT.service;

import com.anil.SpringSecurityJWT.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

// To Create and Validate Token

@Service
public class JwtService {
    private final String SECRET_KEY="2a899b3fdaa76946825a0225617780703fa34fdda27dccb37b49d2b16a4e197f";

    public String generateToken(User user){
        String token= Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    // Creating getSigninKey Method
    private Key getSigninKey() {
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extracting Payload or Claims from token
    private Claims extractAllClaims(String token){
        return Jwts.
                parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // To extract specific claim from payload/claims
    public <T> T extractClaim(String token, Function<Claims, T>resolver){
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);
    }

    // To extract Username from Claim
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // Validate Token
    public boolean isValid(String token, UserDetails user){
        String username=extractUsername(token);
        return (username.equals(user.getUsername()))&&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
