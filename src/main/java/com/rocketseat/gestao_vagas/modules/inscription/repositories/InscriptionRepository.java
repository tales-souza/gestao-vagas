package com.rocketseat.gestao_vagas.modules.inscription.repositories;

import com.rocketseat.gestao_vagas.modules.inscription.entities.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InscriptionRepository  extends JpaRepository<InscriptionEntity, UUID> {
    Optional<InscriptionEntity> findByCandidateIdAndJobId(UUID candidateId, UUID jobId);
}
