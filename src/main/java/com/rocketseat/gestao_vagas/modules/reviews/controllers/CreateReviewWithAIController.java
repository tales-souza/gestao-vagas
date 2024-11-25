package com.rocketseat.gestao_vagas.modules.reviews.controllers;

import com.rocketseat.gestao_vagas.modules.reviews.dtos.CreateReviewAiDto;
import com.rocketseat.gestao_vagas.providers.OpenAiProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/")
public class CreateReviewWithAIController {

    @Autowired
    private OpenAiProvider openAiProvider;

    @PostMapping("/reviews/ai")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<String> analyzeCandidate(@Valid @RequestBody CreateReviewAiDto createReviewAiDto, HttpServletRequest request){
        try {
            String result = openAiProvider.analyzeCandidate(createReviewAiDto.getDescriptionJob(), createReviewAiDto.getCurriculum());
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }





}
