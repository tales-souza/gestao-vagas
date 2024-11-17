package com.rocketseat.gestao_vagas.modules.job.services;

import com.rocketseat.gestao_vagas.modules.job.dtos.ReturnResponseJobDto;
import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import com.rocketseat.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListJobsService {

    @Autowired
    private JobRepository jobRepository;


      public List<ReturnResponseJobDto> execute(UUID companyId, UUID candidateId) {
        List<Object[]> results = this.jobRepository.findAllByCompanyId2(companyId, candidateId);
        return results.stream()
                .map(row -> ReturnResponseJobDto.builder()
                        .id((UUID) row[0])
                        .description((String) row[1])
                        .level((String) row[2])
                        .benefits((String) row[3])
                        .candidateId((UUID) row[4])
                        .build())
                .collect(Collectors.toList());

    }



}
