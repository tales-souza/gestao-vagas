package com.rocketseat.gestao_vagas.modules.company.services;

import com.rocketseat.gestao_vagas.exceptions.UserFoundException;
import com.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.rocketseat.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getUsername())
                .ifPresent((user) -> {
            throw new UserFoundException();
        });

        var newPassword = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(newPassword);

        return this.companyRepository.save(companyEntity);
    }
}
