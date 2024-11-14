package com.rocketseat.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;


    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try{
         var tokenDecoded  = JWT.require(algorithm)
                 .build()
                 .verify(token);

         return tokenDecoded;

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public AuthCompanyResponseDto createToken(String subject){

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(subject)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDto =  AuthCompanyResponseDto.builder()
                .acessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponseDto;
    }

}

