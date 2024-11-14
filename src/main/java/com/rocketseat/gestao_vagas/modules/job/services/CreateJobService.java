package com.rocketseat.gestao_vagas.modules.job.services;

import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import com.rocketseat.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){

        return jobRepository.save(jobEntity);

    }

}
