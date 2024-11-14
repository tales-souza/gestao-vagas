package com.rocketseat.gestao_vagas.modules.company.services;

import com.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyResponseDto;
import com.rocketseat.gestao_vagas.providers.JWTProvider;
import com.rocketseat.gestao_vagas.modules.company.dto.AuthCompanyDto;
import com.rocketseat.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;


    public AuthCompanyResponseDto execute(AuthCompanyDto authCompanyDto) {

        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(
                () -> {
                    throw new BadCredentialsException("Credenciais Inválidas");
                });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new BadCredentialsException("Credenciais Inválidas");
        }

        var authCompanyResponseDto = jwtProvider.createToken(company.getId().toString());

        return authCompanyResponseDto;

    }



}
