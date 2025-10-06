package com.josiasjuniorsantos.AcademiaApi.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.josiasjuniorsantos.AcademiaApi.Anotation.Cpf;
import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;

import java.time.LocalDate;

public class AlunoDTO {

    private Long id;
    private String nome;
    private String email;

    @Cpf
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public PlanoDTO getPlano() { return plano; }
    public void setPlano(PlanoDTO plano) { this.plano = plano; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public static AlunoDTO fromEntity(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setCpf(aluno.getCpf());
        dto.setDataNascimento(aluno.getDataNascimento());
        dto.setAtivo(aluno.isAtivo());

        if (aluno.getPlano() != null) {
            PlanoDTO planoDTO = new PlanoDTO();
            planoDTO.setId(aluno.getPlano().getId());
            planoDTO.setNome(aluno.getPlano().getNome());
            planoDTO.setValorMensal(aluno.getPlano().getValorMensal());
            dto.setPlano(planoDTO);
        }

        return dto;
    }


}
