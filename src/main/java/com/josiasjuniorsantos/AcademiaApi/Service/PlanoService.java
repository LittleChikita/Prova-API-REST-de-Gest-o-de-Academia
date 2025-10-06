package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository repository;

    public Plano salvar(Plano plano) {
        return repository.save(plano);
    }

    public List<Plano> listarTodos() {
        return repository.findAll();
    }

    public Plano buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    public void deletar(Long id) {
        Plano plano = buscarPorId(id);
        if (!plano.getAlunos().isEmpty()) {
            throw new IllegalStateException("Não é possível deletar o plano. Existem alunos vinculados a ele.");
        }
        repository.deleteById(id);
    }
}
