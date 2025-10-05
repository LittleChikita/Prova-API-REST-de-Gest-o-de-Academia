package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/{alunoId}")
    public PagamentoDTO registrarPagamento(@PathVariable Long alunoId,
                                           @RequestParam Pagamento.FormaPagamento formaPagamento,
                                           @RequestParam LocalDateTime dataVencimento) {
        Pagamento pagamento = pagamentoService.salvarPagamento(alunoId, formaPagamento, dataVencimento);
        return converterParaDTO(pagamento);
    }

    @GetMapping
    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoService.listarPagamentos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PagamentoDTO consultarPagamento(@PathVariable Long id) {
        Pagamento pagamento = pagamentoService.consultarPagamento(id);
        return converterParaDTO(pagamento);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<PagamentoDTO> listarPagamentosDoAluno(@PathVariable Long alunoId) {
        return pagamentoService.listarPagamentosDoAluno(alunoId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/aluno/{alunoId}/status")
    public List<PagamentoDTO> listarPagamentosDoAlunoPorStatus(@PathVariable Long alunoId,
                                                               @RequestParam Pagamento.StatusPagamento status) {
        return pagamentoService.listarPagamentosDoAlunoPorStatus(alunoId, status)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private PagamentoDTO converterParaDTO(Pagamento pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setAlunoId(pagamento.getAluno().getId());
        dto.setValorPagamento(pagamento.getValorPagamento());
        dto.setDataPagamento(pagamento.getDataPagamento());
        dto.setStatusPagamento(pagamento.getStatusPagamento());
        dto.setFormaPagamento(pagamento.getFormaPagamento());
        return dto;
    }
}
