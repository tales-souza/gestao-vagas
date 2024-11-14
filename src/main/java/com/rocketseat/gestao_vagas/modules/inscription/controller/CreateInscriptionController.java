package com.rocketseat.gestao_vagas.modules.inscription.controller;

import com.rocketseat.gestao_vagas.modules.inscription.dtos.CreateInscriptionDto;
import com.rocketseat.gestao_vagas.modules.inscription.entities.InscriptionEntity;
import com.rocketseat.gestao_vagas.modules.inscription.services.CreateInscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CreateInscriptionController {

    @Autowired
    private CreateInscriptionService createInscriptionService;

    @PostMapping("/inscription")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> execute(@RequestBody CreateInscriptionDto createInscriptionDto, HttpServletRequest request) {

        try {

            var idCandidate = request.getAttribute("candidate_id").toString();

            var inscription = this.createInscriptionService.execute(createInscriptionDto, UUID.fromString(idCandidate));

            return ResponseEntity.ok().body(inscription);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
