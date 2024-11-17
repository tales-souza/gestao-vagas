package com.rocketseat.gestao_vagas.modules.job.controllers;

import com.rocketseat.gestao_vagas.modules.job.dtos.ReturnResponseJobDto;
import com.rocketseat.gestao_vagas.modules.job.services.ListJobsService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Object> execute(@PathVariable UUID idCompany, HttpServletRequest request){
        try{

            var candidateId = request.getAttribute("candidate_id").toString();
            var jobs = this.listJobsService.execute(idCompany, UUID.fromString(candidateId));
            return ResponseEntity.ok().body(jobs);

        }catch (Exception e) {
            return ResponseEntity.badRequest().body((e.getMessage()));

        }
    }

}
