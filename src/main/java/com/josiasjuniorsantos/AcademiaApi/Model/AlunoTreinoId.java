package com.josiasjuniorsantos.AcademiaApi.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlunoTreinoId implements Serializable {

    private Long alunoId;
    private Long treinoId;

    public AlunoTreinoId() {}

    public AlunoTreinoId(Long alunoId, Long treinoId) {
        this.alunoId = alunoId;
        this.treinoId = treinoId;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(Long treinoId) {
        this.treinoId = treinoId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        AlunoTreinoId that = (AlunoTreinoId) object;
        return Objects.equals(alunoId, that.alunoId) && Objects.equals(treinoId, that.treinoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alunoId, treinoId);
    }
}
