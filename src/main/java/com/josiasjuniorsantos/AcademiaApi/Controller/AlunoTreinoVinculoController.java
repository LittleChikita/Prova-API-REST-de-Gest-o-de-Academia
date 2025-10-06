package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.AlunoTreinoVinculoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import com.josiasjuniorsantos.AcademiaApi.Service.AlunoTreinoVinculoService;
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
@RequestMapping("/api/v1/vinculos")
public class AlunoTreinoVinculoController {

    private final AlunoTreinoVinculoService vinculoService;

    public AlunoTreinoVinculoController(AlunoTreinoVinculoService vinculoService) {
        this.vinculoService = vinculoService;
    }

    @Operation(summary = "Criar vínculo", description = "Cria um vínculo entre aluno e treino.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vínculo criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno ou treino não encontrado")
    })
    @PostMapping
    public ResponseEntity<AlunoTreinoVinculoDTO> criarVinculo(
            @RequestBody @Parameter(description = "DTO contendo IDs do aluno e treino") AlunoTreinoVinculoDTO dto
    ) {
        AlunoTreinoVinculo vinculo = vinculoService.criarVinculo(dto.getAlunoId(), dto.getTreinoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(vinculo));
    }

    @Operation(summary = "Listar vínculos de um aluno", description = "Retorna todos os vínculos de treino de um aluno específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vínculos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AlunoTreinoVinculoDTO>> listarPorAluno(
            @PathVariable @Parameter(description = "ID do aluno") Long alunoId
    ) {
        List<AlunoTreinoVinculoDTO> lista = vinculoService.listarPorAluno(alunoId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Listar vínculos de um treino", description = "Retorna todos os vínculos de aluno de um treino específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vínculos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    @GetMapping("/treino/{treinoId}")
    public ResponseEntity<List<AlunoTreinoVinculoDTO>> listarPorTreino(
            @PathVariable @Parameter(description = "ID do treino") Long treinoId
    ) {
        List<AlunoTreinoVinculoDTO> lista = vinculoService.listarPorTreino(treinoId)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Deletar vínculo", description = "Remove um vínculo entre aluno e treino.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vínculo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno ou treino não encontrado")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarVinculo(
            @RequestBody @Parameter(description = "DTO contendo IDs do aluno e treino") AlunoTreinoVinculoDTO dto
    ) {
        vinculoService.deletarVinculo(dto.getAlunoId(), dto.getTreinoId());
        return ResponseEntity.noContent().build();
    }

    private AlunoTreinoVinculoDTO converterParaDTO(AlunoTreinoVinculo vinculo) {
        AlunoTreinoVinculoDTO dto = new AlunoTreinoVinculoDTO();
        dto.setAlunoId(vinculo.getAluno().getId());
        dto.setTreinoId(vinculo.getTreino().getId());
        dto.setDataAssociacao(vinculo.getDataAssociacao());
        return dto;
    }
}
