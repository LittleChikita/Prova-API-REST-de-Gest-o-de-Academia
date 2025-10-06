package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.AlunoDTO;
import com.josiasjuniorsantos.AcademiaApi.Dto.PlanoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Service.AlunoService;
import com.josiasjuniorsantos.AcademiaApi.Service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final PlanoService planoService;

    public AlunoController(AlunoService alunoService,PlanoService planoService) {
        this.alunoService = alunoService;
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        Plano plano = null;
        if (alunoDTO.getPlano() != null && alunoDTO.getPlano().getId() != null) {
            plano = planoService.buscarPorId(alunoDTO.getPlano().getId());
        }

        Aluno aluno = alunoService.cadastrarAluno(
                alunoDTO.getNome(),
                alunoDTO.getEmail(),
                alunoDTO.getCpf(),
                alunoDTO.getDataNascimento(),
                plano
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(aluno));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> consultarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.consultarAluno(id);
        return ResponseEntity.ok(converterParaDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoDTO> lista = alunoService.listarAlunos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Long id,
                                                   @RequestBody AlunoDTO alunoDTO) {
        Plano plano = null;
        if (alunoDTO.getPlano() != null && alunoDTO.getPlano().getId() != null) {
            plano = planoService.buscarPorId(alunoDTO.getPlano().getId());
        }

        Aluno alunoAtualizado = alunoService.atualizarAluno(
                id,
                alunoDTO.getNome(),
                alunoDTO.getEmail(),
                alunoDTO.getDataNascimento(),
                plano
        );

        return ResponseEntity.ok(converterParaDTO(alunoAtualizado));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<AlunoDTO> inativarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.inativarAluno(id);
        return ResponseEntity.ok(converterParaDTO(aluno));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<AlunoDTO> ativarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.ativarAluno(id);
        return ResponseEntity.ok(converterParaDTO(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }


    private AlunoDTO converterParaDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setCpf(aluno.getCpf());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setAtivo(aluno.isAtivo());

        if (aluno.getPlano() != null) {
            PlanoDTO planoDTO = new PlanoDTO();
            planoDTO.setId(aluno.getPlano().getId());
            planoDTO.setNome(aluno.getPlano().getNome());
            planoDTO.setValorMensal(aluno.getPlano().getValorMensal());
            dto.setPlano(planoDTO);
        }

        return dto;
    }
}
