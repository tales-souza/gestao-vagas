package com.rocketseat.gestao_vagas.modules.job.dtos;

import com.rocketseat.gestao_vagas.modules.company.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnResponseJobDto {

    private UUID id;
    private String description;
    private String level;
    private String benefits;
    private UUID candidateId;
}
