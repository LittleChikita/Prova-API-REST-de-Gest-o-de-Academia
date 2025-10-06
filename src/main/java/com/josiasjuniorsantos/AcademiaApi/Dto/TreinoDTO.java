package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.josiasjuniorsantos.AcademiaApi.Model.Treino.NivelTreino;

public class TreinoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private NivelTreino nivel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public NivelTreino getNivel() { return nivel; }
    public void setNivel(NivelTreino nivel) { this.nivel = nivel; }
}