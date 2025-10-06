package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PlanoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Service.PlanoService;
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
@RequestMapping("/api/v1/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @Operation(summary = "Criar um novo plano", description = "Cria um novo plano com nome e valor mensal.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Plano criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        Plano salvo = planoService.salvar(plano);
        return new ResponseEntity<>(PlanoDTO.fromEntity(salvo), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os planos", description = "Retorna uma lista com todos os planos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        List<PlanoDTO> lista = planoService.listarTodos()
                .stream()
                .map(PlanoDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Buscar plano por ID", description = "Retorna os dados de um plano específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano encontrado"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(
            @PathVariable @Parameter(description = "ID do plano") Long id) {
        Plano plano = planoService.buscarPorId(id);
        return ResponseEntity.ok(PlanoDTO.fromEntity(plano));
    }

    @Operation(summary = "Atualizar plano", description = "Atualiza os dados de um plano existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(
            @PathVariable @Parameter(description = "ID do plano") Long id,
            @RequestBody PlanoDTO dto) {
        Plano plano = planoService.buscarPorId(id);
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        Plano atualizado = planoService.salvar(plano);
        return ResponseEntity.ok(PlanoDTO.fromEntity(atualizado));
    }

    @Operation(summary = "Deletar plano", description = "Remove um plano caso não tenha alunos vinculados.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Plano deletado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Plano possui alunos vinculados e não pode ser deletado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPlano(
            @PathVariable @Parameter(description = "ID do plano") Long id) {
        try {
            planoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
