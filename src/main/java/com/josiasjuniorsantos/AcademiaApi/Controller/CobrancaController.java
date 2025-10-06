package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.CobrancaDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Service.CobrancaService;
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
@RequestMapping("/api/v1/cobrancas")
public class CobrancaController {

    private final CobrancaService cobrancaService;

    public CobrancaController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    @Operation(summary = "Gerar cobrança", description = "Gera uma nova cobrança para um aluno com plano ativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cobrança gerada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Aluno não possui plano ativo")
    })
    @PostMapping("/gerar/{alunoId}")
    public ResponseEntity<CobrancaDTO> gerarCobranca(
            @PathVariable @Parameter(description = "ID do aluno") Long alunoId) {
        try {
            Cobranca cobranca = cobrancaService.gerarCobranca(alunoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(CobrancaDTO.fromEntity(cobranca));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Listar cobranças do aluno", description = "Retorna todas as cobranças de um aluno específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de cobranças retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<CobrancaDTO>> listarCobrancasDoAluno(
            @PathVariable @Parameter(description = "ID do aluno") Long alunoId) {
        List<CobrancaDTO> lista = cobrancaService.listarCobrancasDoAluno(alunoId)
                .stream()
                .map(CobrancaDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Listar cobranças pendentes", description = "Retorna todas as cobranças com status PENDENTE.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de cobranças pendentes retornada com sucesso")
    })
    @GetMapping("/pendentes")
    public ResponseEntity<List<CobrancaDTO>> listarCobrancasPendentes() {
        List<CobrancaDTO> lista = cobrancaService.listarCobrancasPendentes()
                .stream()
                .map(CobrancaDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}
