package com.rocketseat.gestao_vagas.modules.candidate.controllers;

import com.rocketseat.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocketseat.gestao_vagas.modules.candidate.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CreateCandidateController {

    @Autowired
    private CandidateService candidadeService;

    @PostMapping("/")
    public ResponseEntity<Object> execute(@Valid @RequestBody CandidateEntity candidate) {
        try{
            var result = this.candidadeService.create(candidate);
            return ResponseEntity.ok().body(result) ;
        }catch (Exception e ) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }
}


