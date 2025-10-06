package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.TreinoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Treino;
import com.josiasjuniorsantos.AcademiaApi.Service.TreinoService;
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
@RequestMapping("/api/v1/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @Operation(summary = "Cadastrar treino", description = "Cadastra um novo treino na academia.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Treino cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TreinoDTO> cadastrarTreino(@RequestBody TreinoDTO treinoDTO) {
        Treino treino = treinoService.cadastrarTreino(
                treinoDTO.getNome(),
                treinoDTO.getDescricao(),
                treinoDTO.getNivel()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(treino));
    }

    @Operation(summary = "Atualizar treino", description = "Atualiza um treino existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Treino atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(
            @PathVariable @Parameter(description = "ID do treino a ser atualizado") Long id,
            @RequestBody TreinoDTO treinoDTO) {
        Treino treino = treinoService.atualizarTreino(
                id,
                treinoDTO.getNome(),
                treinoDTO.getDescricao(),
                treinoDTO.getNivel()
        );
        return ResponseEntity.ok(converterParaDTO(treino));
    }

    @Operation(summary = "Listar treinos", description = "Retorna todos os treinos cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de treinos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        List<TreinoDTO> lista = treinoService.listarTreinos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Remover treino", description = "Remove um treino pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Treino removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTreino(
            @PathVariable @Parameter(description = "ID do treino a ser removido") Long id) {
        treinoService.removerTreino(id);
        return ResponseEntity.noContent().build();
    }

    private TreinoDTO converterParaDTO(Treino treino) {
        TreinoDTO dto = new TreinoDTO();
        dto.setId(treino.getId());
        dto.setNome(treino.getNome());
        dto.setDescricao(treino.getDescricao());
        dto.setNivel(treino.getNivel());
        return dto;
    }
}
