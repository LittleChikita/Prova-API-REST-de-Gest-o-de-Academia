package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.TreinoAvulsoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/treinos-avulsos")
public class TreinoAvulsoController {

    private final TreinoAvulsoService treinoAvulsoService;

    public TreinoAvulsoController(TreinoAvulsoService treinoAvulsoService) {
        this.treinoAvulsoService = treinoAvulsoService;
    }

    @Operation(
            summary = "Registrar treino avulso",
            description = "Registra um treino avulso para um visitante, informando nome, email e forma de pagamento."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Treino avulso registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PagamentoDTO> registrarTreinoAvulso(
            @RequestParam @Parameter(description = "Nome do visitante") String nome,
            @RequestParam @Parameter(description = "Email do visitante") String email,
            @RequestParam @Parameter(description = "Forma de pagamento") Pagamento.FormaPagamento formaPagamento
    ) {
        Pagamento pagamento = treinoAvulsoService.registrarTreinoAvulso(nome, email, formaPagamento);
        PagamentoDTO dto = PagamentoDTO.fromEntity(pagamento);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Listar treinos avulsos",
            description = "Lista todos os treinos avulsos registrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de treinos avulsos retornada com sucesso")
    })
    @GetMapping
    public List<PagamentoDTO> listarTreinosAvulsos() {
        return treinoAvulsoService.listarTreinosAvulsos()
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
