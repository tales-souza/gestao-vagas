package com.rocketseat.gestao_vagas.modules.candidate.services;


import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.rocketseat.gestao_vagas.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateService {

    @Autowired
    private CandidateRepository candidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidadeRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var newPassword = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(newPassword);

        return this.candidadeRepository.save(candidate);
    }
}
