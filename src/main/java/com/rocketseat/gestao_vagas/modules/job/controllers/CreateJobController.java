package com.rocketseat.gestao_vagas.modules.job.controllers;


import com.rocketseat.gestao_vagas.modules.job.dtos.CreateJobDto;
import com.rocketseat.gestao_vagas.modules.job.entities.JobEntity;
import com.rocketseat.gestao_vagas.modules.job.services.CreateJobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class CreateJobController {

    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request){
        try{
            var companyId = request.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .benefits(createJobDto.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDto.getDescription())
                    .level(createJobDto.getLevel())
                    .build();

            var result = this.createJobService.execute(jobEntity);

            return ResponseEntity.ok().body(result) ;
        }catch (Exception e ) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }
}
