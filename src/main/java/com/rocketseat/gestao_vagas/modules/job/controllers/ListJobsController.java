package com.rocketseat.gestao_vagas.modules.job.controllers;

import com.rocketseat.gestao_vagas.modules.job.dtos.ReturnResponseJobDto;
import com.rocketseat.gestao_vagas.modules.job.services.ListJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/candidate/job")
public class ListJobsController {

    @Autowired
    private ListJobsService listJobsService;

    @GetMapping("/{idCompany}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> execute(@PathVariable UUID idCompany){
        try{

            var jobs = this.listJobsService.execute(idCompany);

            var jobsDto = jobs.stream().
                    map(job -> ReturnResponseJobDto.builder()
                            .benefits(job.getBenefits())
                            .level(job.getLevel())
                            .id(job.getId())
                            .description(job.getDescription())
                            .build())
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(jobsDto);


        }catch (Exception e) {
            return ResponseEntity.badRequest().body((e.getMessage()));

        }
    }

}
