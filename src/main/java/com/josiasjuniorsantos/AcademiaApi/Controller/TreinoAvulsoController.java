package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PagamentoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Service.TreinoAvulsoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/treinos-avulsos")
public class TreinoAvulsoController {

    private final TreinoAvulsoService treinoAvulsoService;

    public TreinoAvulsoController(TreinoAvulsoService treinoAvulsoService) {
        this.treinoAvulsoService = treinoAvulsoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> registrarTreinoAvulso(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam Pagamento.FormaPagamento formaPagamento
    ) {
        Pagamento pagamento = treinoAvulsoService.registrarTreinoAvulso(nome, email, formaPagamento);
        PagamentoDTO dto = PagamentoDTO.fromEntity(pagamento);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<PagamentoDTO> listarTreinosAvulsos() {
        return treinoAvulsoService.listarTreinosAvulsos()
                .stream()
                .map(PagamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }

}