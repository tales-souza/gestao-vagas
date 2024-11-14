package com.rocketseat.gestao_vagas.modules.candidate.services;


import com.rocketseat.gestao_vagas.modules.candidate.dtos.ProfileCandidateResponseDto;
import com.rocketseat.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateService {


    @Autowired
    private CandidateRepository candidateRepository;


    public ProfileCandidateResponseDto execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow( () -> {
                   throw new UsernameNotFoundException("User not found");
                });

        var candidateDto = ProfileCandidateResponseDto.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .build();

        return candidateDto;

    }

}
