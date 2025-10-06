package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Registrar pagamento", description = "Registra um pagamento para uma cobrança específica de um aluno.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pagamento registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cobrança não encontrada")
    })
    @PostMapping
    public ResponseEntity<PagamentoDTO> registrarPagamento(
            @RequestParam @Parameter(description = "ID da cobrança") Long cobrancaId,
            @RequestParam @Parameter(description = "Forma de pagamento") Pagamento.FormaPagamento formaPagamento
    ) {
        Pagamento pagamento = pagamentoService.registrarPagamento(cobrancaId, formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoDTO.fromEntity(pagamento));
    }

    @Operation(summary = "Listar todos os pagamentos", description = "Retorna todos os pagamentos registrados na academia.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        List<PagamentoDTO> lista = pagamentoService.listarPagamentos()
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Listar pagamentos de um aluno", description = "Retorna todos os pagamentos de um aluno específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos do aluno retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosDoAluno(
            @PathVariable @Parameter(description = "ID do aluno") Long alunoId
    ) {
        List<PagamentoDTO> lista = pagamentoService.listarPagamentosDoAluno(alunoId)
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Consultar pagamento", description = "Retorna os detalhes de um pagamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> consultarPagamento(
            @PathVariable @Parameter(description = "ID do pagamento") Long id
    ) {
        Pagamento pagamento = pagamentoService.consultarPagamento(id);
        return ResponseEntity.ok(PagamentoDTO.fromEntity(pagamento));
    }
}
