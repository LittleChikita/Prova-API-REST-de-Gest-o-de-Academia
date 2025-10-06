package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.CobrancaDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Service.CobrancaService;
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

    @PostMapping("/gerar/{alunoId}")
    public CobrancaDTO gerarCobranca(@PathVariable Long alunoId) {
        Cobranca cobranca = cobrancaService.gerarCobranca(alunoId);
        return CobrancaDTO.fromEntity(cobranca);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<CobrancaDTO> listarCobrancasDoAluno(@PathVariable Long alunoId) {
        return cobrancaService.listarCobrancasDoAluno(alunoId)
                .stream()
                .map(CobrancaDTO::fromEntity)
                .toList();
    }

    @GetMapping("/pendentes")
    public List<CobrancaDTO> listarCobrancasPendentes() {
        return cobrancaService.listarCobrancasPendentes()
                .stream()
                .map(CobrancaDTO::fromEntity)
                .collect(Collectors.toList());
    }


}
