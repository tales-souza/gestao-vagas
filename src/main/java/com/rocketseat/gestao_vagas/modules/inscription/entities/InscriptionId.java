package com.rocketseat.gestao_vagas.modules.inscription.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InscriptionId implements Serializable {
    private UUID candidateId;
    private UUID jobId;
}
