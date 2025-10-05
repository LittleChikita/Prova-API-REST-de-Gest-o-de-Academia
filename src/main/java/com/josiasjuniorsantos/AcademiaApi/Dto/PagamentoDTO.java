package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO {

    private Long alunoId;
    private BigDecimal valorPagamento;
    private LocalDateTime dataPagamento;
    private Pagamento.StatusPagamento statusPagamento;
    private Pagamento.FormaPagamento formaPagamento;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }

    public BigDecimal getValorPagamento() { return valorPagamento; }
    public void setValorPagamento(BigDecimal valorPagamento) { this.valorPagamento = valorPagamento; }

    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }

    public Pagamento.StatusPagamento getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(Pagamento.StatusPagamento statusPagamento) { this.statusPagamento = statusPagamento; }

    public Pagamento.FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(Pagamento.FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
}
