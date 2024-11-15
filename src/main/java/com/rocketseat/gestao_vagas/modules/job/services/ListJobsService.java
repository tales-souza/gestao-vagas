package com.rocketseat.gestao_vagas.modules.job.services;

import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import com.rocketseat.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListJobsService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(UUID companyId) {

        return this.jobRepository.findAllByCompanyId(companyId);

    }

}
