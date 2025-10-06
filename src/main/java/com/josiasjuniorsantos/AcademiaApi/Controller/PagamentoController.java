package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.PagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public PagamentoDTO registrarPagamento(@RequestParam Long cobrancaId,
                                           @RequestParam Pagamento.FormaPagamento formaPagamento) {
        Pagamento pagamento = pagamentoService.registrarPagamento(cobrancaId, formaPagamento);
        return PagamentoDTO.fromEntity(pagamento);
    }

    @GetMapping
    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoService.listarPagamentos()
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/aluno/{alunoId}")
    public List<PagamentoDTO> listarPagamentosDoAluno(@PathVariable Long alunoId) {
        return pagamentoService.listarPagamentosDoAluno(alunoId)
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PagamentoDTO consultarPagamento(@PathVariable Long id) {
        Pagamento pagamento = pagamentoService.consultarPagamento(id);
        return PagamentoDTO.fromEntity(pagamento);
    }
}

