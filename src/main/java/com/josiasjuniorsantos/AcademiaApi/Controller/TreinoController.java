package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.AlunoTreinoVinculoDTO;
import com.josiasjuniorsantos.AcademiaApi.Dto.TreinoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Treino;
import com.josiasjuniorsantos.AcademiaApi.Service.TreinoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public TreinoDTO cadastrarTreino(@RequestBody TreinoDTO treinoDTO) {
        Treino treino = treinoService.cadastrarTreino(
                treinoDTO.getNome(),
                treinoDTO.getDescricao(),
                treinoDTO.getNivel()
        );
        return converterParaDTO(treino);
    }

    @PutMapping("/{id}")
    public TreinoDTO atualizarTreino(@PathVariable Long id, @RequestBody TreinoDTO treinoDTO) {
        Treino treino = treinoService.atualizarTreino(
                id,
                treinoDTO.getNome(),
                treinoDTO.getDescricao(),
                treinoDTO.getNivel()
        );
        return converterParaDTO(treino);
    }

    @GetMapping
    public List<TreinoDTO> listarTreinos() {
        return treinoService.listarTreinos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void removerTreino(@PathVariable Long id) {
        treinoService.removerTreino(id);
    }


    @PostMapping("/vincular")
    public void vincularAlunoTreino(@RequestParam Long alunoId, @RequestParam Long treinoId) {
        treinoService.vincularAlunoTreino(alunoId, treinoId);
    }

    @DeleteMapping("/vinculo")
    public void removerVinculo(@RequestParam Long alunoId, @RequestParam Long treinoId) {
        treinoService.removerVinculo(alunoId, treinoId);
    }

    private TreinoDTO converterParaDTO(Treino treino) {
        TreinoDTO dto = new TreinoDTO();
        dto.setNome(treino.getNome());
        dto.setDescricao(treino.getDescricao());
        dto.setNivel(treino.getNivel());
        return dto;
    }



}
