package com.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.rocketseat.gestao_vagas.modules.candidate.dtos.CreateCandidadeRequestDto;
import com.rocketseat.gestao_vagas.modules.candidate.dtos.UpdateCandidateRequestDto;
import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.candidate.services.CreateCandidateService;
import com.rocketseat.gestao_vagas.modules.candidate.services.UpdateCandidateService;
import com.rocketseat.gestao_vagas.providers.S3StotageProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class UpdateCandidateController {

    @Autowired
    private UpdateCandidateService candidadeService;

    @Autowired
    private S3StotageProvider s3StotageProvider;

    @PutMapping(value = "/" )
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> execute(@Valid @RequestPart("metadata") UpdateCandidateRequestDto candidate,
                                          @RequestPart(value = "file", required = false) MultipartFile file,
                                          HttpServletRequest request
                                          ) {
        try{

            var candidateId = request.getAttribute("candidate_id").toString();

            String fileUrl = null;

            if(!file.isEmpty()){
                fileUrl = this.s3StotageProvider.uploadFile(file);
            }
            var candidateEntity = CandidateEntity
                    .builder()
                    .name(candidate.getName())
                    .description(candidate.getDescription())
                    .password(candidate.getPassword())
                    .curriculum(fileUrl)
                    .build();

            var result = this.candidadeService.execute(candidateEntity, UUID.fromString(candidateId));

            return ResponseEntity.ok().body(result) ;
        }catch (Exception e ) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }
}
