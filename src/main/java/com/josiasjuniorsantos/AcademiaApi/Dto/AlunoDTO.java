package com.josiasjuniorsantos.AcademiaApi.Dto;

import java.util.Date;

public class AlunoDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private PlanoDTO plano;
    private boolean ativo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public PlanoDTO getPlano() { return plano; }
    public void setPlano(PlanoDTO plano) { this.plano = plano; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
