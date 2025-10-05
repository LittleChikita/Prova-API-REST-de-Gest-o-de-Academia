package com.josiasjuniorsantos.AcademiaApi.Dto;

import java.time.LocalDateTime;

public class AlunoTreinoVinculoDTO {

    private Long alunoId;
    private Long treinoId;
    private LocalDateTime dataAssociacao;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }

    public Long getTreinoId() { return treinoId; }
    public void setTreinoId(Long treinoId) { this.treinoId = treinoId; }

    public LocalDateTime getDataAssociacao() { return dataAssociacao; }
    public void setDataAssociacao(LocalDateTime dataAssociacao) { this.dataAssociacao = dataAssociacao; }
}
