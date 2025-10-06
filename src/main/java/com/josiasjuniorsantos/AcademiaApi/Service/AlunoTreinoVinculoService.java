package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoId;
import com.josiasjuniorsantos.AcademiaApi.Model.Treino;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoTreinoVinculoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.TreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlunoTreinoVinculoService {

    private final AlunoTreinoVinculoRepository vinculoRepository;
    private final AlunoRepository alunoRepository;
    private final TreinoRepository treinoRepository;

    public AlunoTreinoVinculoService(AlunoTreinoVinculoRepository vinculoRepository,
                                     AlunoRepository alunoRepository,
                                     TreinoRepository treinoRepository) {
        this.vinculoRepository = vinculoRepository;
        this.alunoRepository = alunoRepository;
        this.treinoRepository = treinoRepository;
    }

    @Transactional
    public AlunoTreinoVinculo criarVinculo(Long alunoId, Long treinoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + alunoId));
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado com id: " + treinoId));

        AlunoTreinoId id = new AlunoTreinoId(alunoId, treinoId);

        if (vinculoRepository.existsById(id)) {
            throw new RuntimeException("Vínculo já existe entre aluno e treino.");
        }

        AlunoTreinoVinculo vinculo = new AlunoTreinoVinculo();
        vinculo.setId(id);
        vinculo.setAluno(aluno);
        vinculo.setTreino(treino);
        vinculo.setDataAssociacao(LocalDateTime.now());

        return vinculoRepository.save(vinculo);
    }

    @Transactional(readOnly = true)
    public List<AlunoTreinoVinculo> listarPorAluno(Long alunoId) {
        return vinculoRepository.findByAlunoId(alunoId);
    }

    @Transactional(readOnly = true)
    public List<AlunoTreinoVinculo> listarPorTreino(Long treinoId) {
        return vinculoRepository.findByTreinoId(treinoId);
    }

    @Transactional
    public void deletarVinculo(Long alunoId, Long treinoId) {
        AlunoTreinoId id = new AlunoTreinoId(alunoId, treinoId);
        AlunoTreinoVinculo vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado entre aluno e treino."));
        vinculoRepository.delete(vinculo);
    }

}
