package com.rocketseat.gestao_vagas.modules.candidate.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rocketseat.gestao_vagas.modules.candidate.dtos.AuthCandidateRequestDto;
import com.rocketseat.gestao_vagas.modules.candidate.dtos.AuthCandidateResponseDto;
import com.rocketseat.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.tokencandidate.secret}")
    private String secretKey;


    public AuthCandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) {

        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDto.username()).orElseThrow(() -> {
            throw new BadCredentialsException("Credenciais Inválidas");
        });

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDto.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new BadCredentialsException("Credenciais Inválidas");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withExpiresAt(expiresIn)
                .sign(algorithm);


        var authCandidateResponde = AuthCandidateResponseDto.builder()
                .acessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponde;


    }


}
