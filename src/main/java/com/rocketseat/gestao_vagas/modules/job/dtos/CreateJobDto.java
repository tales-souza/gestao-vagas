package com.rocketseat.gestao_vagas.modules.job.dtos;

import lombok.Data;

@Data
public class CreateJobDto {

    private String description;
    private String benefits;
    private String level;

}
