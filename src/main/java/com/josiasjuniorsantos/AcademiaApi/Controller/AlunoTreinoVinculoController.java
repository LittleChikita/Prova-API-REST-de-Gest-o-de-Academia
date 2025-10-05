package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.AlunoTreinoVinculoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import com.josiasjuniorsantos.AcademiaApi.Service.TreinoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vinculos")
public class AlunoTreinoVinculoController {

    private final TreinoService treinoService;

    public AlunoTreinoVinculoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }


    private AlunoTreinoVinculoDTO converterParaDTO(AlunoTreinoVinculo vinculo) {
        AlunoTreinoVinculoDTO dto = new AlunoTreinoVinculoDTO();
        dto.setAlunoId(vinculo.getAluno().getId());
        dto.setTreinoId(vinculo.getTreino().getId());
        dto.setDataAssociacao(vinculo.getDataAssociacao());
        return dto;
    }
}
