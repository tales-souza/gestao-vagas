package com.rocketseat.gestao_vagas.modules.job.repositories;

import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
