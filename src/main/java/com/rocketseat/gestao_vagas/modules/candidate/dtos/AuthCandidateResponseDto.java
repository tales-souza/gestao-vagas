package com.rocketseat.gestao_vagas.modules.candidate.dtos;

import com.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCandidateResponseDto {

    private String acessToken;
    private Long expiresIn;

}
