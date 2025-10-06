package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Endpoint para registrar um pagamento baseado em uma cobrança existente
    @PostMapping
    public PagamentoDTO registrarPagamento(@RequestParam Long cobrancaId,
                                           @RequestParam Pagamento.FormaPagamento formaPagamento) {
        Pagamento pagamento = pagamentoService.registrarPagamento(cobrancaId, formaPagamento);
        return converterParaDTO(pagamento);
    }

    // Listar todos os pagamentos
    @GetMapping
    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoService.listarPagamentos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Listar pagamentos de um aluno específico
    @GetMapping("/aluno/{alunoId}")
    public List<PagamentoDTO> listarPagamentosDoAluno(@PathVariable Long alunoId) {
        return pagamentoService.listarPagamentosDoAluno(alunoId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Consultar um pagamento específico
    @GetMapping("/{id}")
    public PagamentoDTO consultarPagamento(@PathVariable Long id) {
        Pagamento pagamento = pagamentoService.consultarPagamento(id);
        return converterParaDTO(pagamento);
    }


    private PagamentoDTO converterParaDTO(Pagamento pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setAlunoId(pagamento.getAluno().getId());
        dto.setValor(pagamento.getCobranca().getValor());
        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setFormaPagamento(pagamento.getFormaPagamento());
        dto.setStatus(pagamento.getCobranca().getStatus());
        dto.setDataVencimento(pagamento.getCobranca().getDataVencimento());
        return dto;
    }
}
