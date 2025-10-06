package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca.StatusCobranca;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CobrancaDTO {

    private Long id;
    private Long alunoId;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private StatusCobranca status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public StatusCobranca getStatus() {
        return status;
    }

    public void setStatus(StatusCobranca status) {
        this.status = status;
    }

    public static CobrancaDTO fromEntity(Cobranca cobranca) {
        CobrancaDTO dto = new CobrancaDTO();
        dto.setId(cobranca.getId());
        dto.setAlunoId(cobranca.getAluno().getId());
        dto.setValor(cobranca.getValor());
        dto.setDataVencimento(cobranca.getDataVencimento());
        dto.setStatus(cobranca.getStatus());
        return dto;
    }
}
