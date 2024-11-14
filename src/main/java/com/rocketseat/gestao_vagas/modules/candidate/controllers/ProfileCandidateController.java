package com.rocketseat.gestao_vagas.modules.candidate.controllers;


import com.rocketseat.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class ProfileCandidateController {

    @Autowired
    private ProfileCandidateService profileService;


    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> execute(HttpServletRequest request){

        var idCandidate = request.getAttribute("candidate_id");

        try{
            var candidate = this.profileService.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(candidate);

        }catch(Exception e) {
            return ResponseEntity.badRequest().body((e.getMessage()));

        }

    }


}
