package com.josiasjuniorsantos.AcademiaApi.Service;


import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoId;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import com.josiasjuniorsantos.AcademiaApi.Model.Treino;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoTreinoVinculoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.TreinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final AlunoRepository alunoRepository;
    private final AlunoTreinoVinculoRepository vinculoRepository;

    public TreinoService(TreinoRepository treinoRepository, AlunoRepository alunoRepository, AlunoTreinoVinculoRepository vinculoRepository) {
        this.treinoRepository = treinoRepository;
        this.alunoRepository = alunoRepository;
        this.vinculoRepository = vinculoRepository;
    }

    public Treino cadastrarTreino(String nome, String descricao, Treino.NivelTreino nivel) {
        Treino treino = new Treino();
        treino.setNome(nome);
        treino.setDescricao(descricao);
        treino.setNivel(nivel);
        return treinoRepository.save(treino);
    }


    public Treino atualizarTreino(Long id, String nome, String descricao, Treino.NivelTreino nivel) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treino não encontrado"));
        treino.setNome(nome);
        treino.setDescricao(descricao);
        treino.setNivel(nivel);
        return treinoRepository.save(treino);
    }

    public List<Treino> listarTreinos() {
        return treinoRepository.findAll();
    }

    public Treino consultarTreinoPorId(Long id) {
        return treinoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treino não encontrado"));
    }

    @Transactional
    public void removerTreino(Long treinoId) {
        Treino treino = treinoRepository.findById(treinoId).orElseThrow(() -> new RuntimeException("Treino nao encontrado"));

        if(!treino.getVinculos().isEmpty()){
            throw new RuntimeException("Não é possível remover treino: Existem alunos associados");
        }
        treinoRepository.delete(treino);
    }

    public boolean possuiAlunos(Treino treino) {
        return !treino.getVinculos().isEmpty();
    }

    @Transactional
    public AlunoTreinoVinculo vincularAlunoTreino(Long alunoId, Long treinoId) {
        if (vinculoRepository.existsByAlunoIdAndTreinoId(alunoId, treinoId)) {
            throw new IllegalStateException("Aluno já vinculado a este treino");
        }

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new IllegalArgumentException("Treino não encontrado"));

        AlunoTreinoVinculo vinculo = new AlunoTreinoVinculo();
        vinculo.setAluno(aluno);
        vinculo.setTreino(treino);
        vinculo.setDataAssociacao(LocalDateTime.now());
        vinculo.setId(new AlunoTreinoId(alunoId, treinoId));

        return vinculoRepository.save(vinculo);
    }

    public List<AlunoTreinoVinculo> listarVinculosDoAluno(Long alunoId) {
        return vinculoRepository.findByAlunoId(alunoId);
    }

    public List<AlunoTreinoVinculo> listarVinculosDoTreino(Long treinoId) {
        return vinculoRepository.findByTreinoId(treinoId);
    }

    @Transactional
    public void removerVinculo(Long alunoId, Long treinoId) {
        AlunoTreinoId id = new AlunoTreinoId(alunoId, treinoId);
        if (!vinculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vínculo não encontrado");
        }
        vinculoRepository.deleteById(id);
    }
}
