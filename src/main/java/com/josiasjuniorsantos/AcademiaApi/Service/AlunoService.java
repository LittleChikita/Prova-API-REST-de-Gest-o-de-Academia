package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;

import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno cadastrarAluno(String nome, String email, String cpf, LocalDate dataNascimento, Plano plano) {
        String cpfNormalizado = normalizarCpf(cpf);
        boolean existe = alunoRepository.existsByCpf(cpf);
        if (existe) {
            throw new IllegalStateException("CPF já cadastrado.");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setCpf(cpfNormalizado);
        aluno.setDataNascimento(dataNascimento);
        aluno.setPlano(plano);
        aluno.setAtivo(true);

        return alunoRepository.save(aluno);
    }


    @Transactional
    public Aluno inativarAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setAtivo(false);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno ativarAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setAtivo(true);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno consultarAluno(Long alunoId) {
        return alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @Transactional
    public Aluno atualizarAluno(Long alunoId, String nome, String email, LocalDate dataNascimento, Plano plano) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setDataNascimento(dataNascimento);
        aluno.setPlano(plano);

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public void deletarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
        alunoRepository.delete(aluno);
    }

    private String normalizarCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", "");
    }



}
