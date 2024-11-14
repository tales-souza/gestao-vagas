package com.rocketseat.gestao_vagas.modules.inscription.enums;

public enum StatusEnum {
    ANALISE("análise"),
    APROVADO("aprovado"),
    REPROVADO("reprovado");

    private String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
