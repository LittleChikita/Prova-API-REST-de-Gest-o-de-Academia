package com.josiasjuniorsantos.AcademiaApi.Service;


import com.josiasjuniorsantos.AcademiaApi.Model.Treino;
import com.josiasjuniorsantos.AcademiaApi.Repository.TreinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
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

}
