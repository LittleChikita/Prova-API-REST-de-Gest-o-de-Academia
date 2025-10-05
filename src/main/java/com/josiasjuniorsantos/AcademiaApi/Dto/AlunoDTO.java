package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import java.util.Date;

public class AlunoDTO {

    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private Plano plano;
    private boolean ativo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
