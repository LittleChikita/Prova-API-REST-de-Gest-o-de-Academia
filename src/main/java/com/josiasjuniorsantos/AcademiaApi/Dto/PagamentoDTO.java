package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PagamentoDTO {

    private Long id;
    private Long alunoId;
    private LocalDateTime dataPagamento;
    private Pagamento.FormaPagamento formaPagamento;

    private Long cobrancaId;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private Cobranca.StatusCobranca status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }

    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }

    public Pagamento.FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(Pagamento.FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }

    public Long getCobrancaId() { return cobrancaId; }
    public void setCobrancaId(Long cobrancaId) { this.cobrancaId = cobrancaId; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public Cobranca.StatusCobranca getStatus() { return status; }
    public void setStatus(Cobranca.StatusCobranca status) { this.status = status; }

    public static PagamentoDTO fromEntity(Pagamento pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(pagamento.getId());
        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setFormaPagamento(pagamento.getFormaPagamento());
        dto.setAlunoId(pagamento.getAluno() != null ? pagamento.getAluno().getId() : null);
        dto.setCobrancaId(pagamento.getCobranca() != null ? pagamento.getCobranca().getId() : null);
        return dto;
    }
}
