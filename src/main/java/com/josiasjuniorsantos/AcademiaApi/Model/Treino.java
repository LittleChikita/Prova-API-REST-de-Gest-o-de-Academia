package com.josiasjuniorsantos.AcademiaApi.Model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    public enum NivelTreino {
        INICIANTE,
        INTERMEDIARIO,
        AVANCADO
    }

    @Enumerated(EnumType.STRING)
    private NivelTreino nivel;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlunoTreinoVinculo> vinculos = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NivelTreino getNivel() {
        return nivel;
    }

    public void setNivel(NivelTreino nivel) {
        this.nivel = nivel;
    }

    public List<AlunoTreinoVinculo> getVinculos() {
        return vinculos;
    }
    public void setVinculos(List<AlunoTreinoVinculo> vinculos) {
        this.vinculos = vinculos;
    }
}
