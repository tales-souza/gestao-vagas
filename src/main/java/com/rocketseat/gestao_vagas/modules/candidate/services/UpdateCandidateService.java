package com.rocketseat.gestao_vagas.modules.candidate.services;

import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CandidateEntity execute(CandidateEntity candidate, UUID candidateId) {

        var candidateEntity = this.candidateRepository.getReferenceById(candidateId);

        candidateEntity.setName(candidate.getName() != null ? candidate.getName() : candidateEntity.getName());
        candidateEntity.setDescription(candidate.getDescription() != null ? candidate.getDescription() : candidateEntity.getDescription());
        candidateEntity.setPassword(candidate.getPassword() != null ? this.passwordEncoder.encode(candidate.getPassword()) : candidateEntity.getPassword());
        candidateEntity.setCurriculum(candidate.getCurriculum() != null ? candidate.getCurriculum() : candidateEntity.getCurriculum());

        return this.candidateRepository.save(candidateEntity);

    }


}
