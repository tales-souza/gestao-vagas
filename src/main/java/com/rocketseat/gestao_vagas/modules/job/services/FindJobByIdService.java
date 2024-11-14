package com.rocketseat.gestao_vagas.modules.job.services;


import com.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import com.rocketseat.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import com.rocketseat.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindJobByIdService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(UUID idJob) {

        var job = this.jobRepository.findById(idJob).orElseThrow(() -> {
            throw new RuntimeException("Job not found");
        });

        return job;

    }

}
