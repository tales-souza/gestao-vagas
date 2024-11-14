package com.rocketseat.gestao_vagas.modules.inscription.services;


import com.rocketseat.gestao_vagas.modules.inscription.dtos.CreateInscriptionDto;
import com.rocketseat.gestao_vagas.modules.inscription.enums.StatusEnum;
import com.rocketseat.gestao_vagas.modules.job.services.FindJobByIdService;
import com.rocketseat.gestao_vagas.modules.inscription.entities.InscriptionEntity;
import com.rocketseat.gestao_vagas.modules.inscription.repositories.InscriptionRepository;
import com.rocketseat.gestao_vagas.providers.EmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateInscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private FindJobByIdService findJobByIdService;

    @Autowired
    private EmailProvider emailProvider;

    public InscriptionEntity execute(CreateInscriptionDto createInscriptionDto, UUID idCandidate) throws Exception {

        this.emailProvider.sendEmailStatus(StatusEnum.ANALISE);

        this.findJobByIdService.execute(createInscriptionDto.jobId());

        var inscription = this.inscriptionRepository.findByCandidateIdAndJobId(idCandidate, createInscriptionDto.jobId());

        if (inscription.isPresent()){
            throw new Exception("Inscription Already exists");
        }

        var inscriptionEntity = InscriptionEntity.builder()
                .jobId(createInscriptionDto.jobId())
                .candidateId(idCandidate)
                .status(StatusEnum.ANALISE)
                .build();

        return this.inscriptionRepository.save(inscriptionEntity);

    }

}
