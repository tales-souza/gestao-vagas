package com.rocketseat.gestao_vagas.modules.reviews.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewAiDto {

    @NotBlank(message = "O Campo [descriptionJob] é obrigatório]")
    private String descriptionJob;

    @NotBlank(message = "O Campo [curriculum] é obrigatório]")
    private String curriculum;

}
