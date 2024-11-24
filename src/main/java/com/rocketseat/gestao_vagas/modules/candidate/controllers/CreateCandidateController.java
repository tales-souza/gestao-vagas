package com.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.rocketseat.gestao_vagas.modules.candidate.dtos.CreateCandidadeRequestDto;
import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.candidate.services.CreateCandidateService;
import com.rocketseat.gestao_vagas.providers.S3StotageProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/candidate")
public class CreateCandidateController {

    @Autowired
    private CreateCandidateService candidadeService;

    @Autowired
    private S3StotageProvider s3StotageProvider;

    @PostMapping(value = "/" )
    public ResponseEntity<Object> execute(@Valid @RequestPart("metadata") CreateCandidadeRequestDto candidate,
                                          @RequestPart(value = "file", required = false) MultipartFile file) {
        try{

            String fileUrl = null;

            if(file != null){
                if(!file.isEmpty()){
                    fileUrl = this.s3StotageProvider.uploadFile(file);
                }
            }


            var candidateEntity = CandidateEntity
                    .builder()
                    .email(candidate.getEmail())
                    .name(candidate.getName())
                    .description(candidate.getDescription())
                    .password(candidate.getPassword())
                    .username(candidate.getUsername())
                    .curriculum(fileUrl)
                    .build();

            var result = this.candidadeService.execute(candidateEntity);

            return ResponseEntity.ok().body(result) ;
        }catch (Exception e ) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }
}


