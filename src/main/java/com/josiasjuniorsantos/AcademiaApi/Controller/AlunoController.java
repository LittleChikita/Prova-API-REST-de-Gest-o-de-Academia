package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.AlunoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoDTO cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoService.cadastrarAluno(
                alunoDTO.getNome(),
                alunoDTO.getEmail(),
                alunoDTO.getCpf(),
                alunoDTO.getDataNascimento(),
                alunoDTO.getPlano()
        );

        return converterParaDTO(aluno);
    }

    @GetMapping("/{id}")
    public AlunoDTO consultarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.consultarAluno(id);
        return converterParaDTO(aluno);
    }

    @PutMapping("/{id}")
    public AlunoDTO atualizarAluno(@PathVariable Long id,
                                   @RequestBody AlunoDTO alunoDTO) {
        Aluno alunoAtualizado = alunoService.atualizarAluno(
                id,
                alunoDTO.getNome(),
                alunoDTO.getEmail(),
                alunoDTO.getDataNascimento(),
                alunoDTO.getPlano()
        );
        return converterParaDTO(alunoAtualizado);
    }

    @PatchMapping("/{id}/inativar")
    public AlunoDTO inativarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.inativarAluno(id);
        return converterParaDTO(aluno);
    }

    @PatchMapping("/{id}/ativar")
    public AlunoDTO ativarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.ativarAluno(id);
        return converterParaDTO(aluno);
    }

    private AlunoDTO converterParaDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setCpf(aluno.getCpf());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setPlano(aluno.getPlano());
        dto.setAtivo(aluno.isAtivo());
        return dto;
    }
}
