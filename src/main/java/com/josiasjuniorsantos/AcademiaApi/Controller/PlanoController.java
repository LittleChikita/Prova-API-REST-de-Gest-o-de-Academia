package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Dto.PlanoDTO;
import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        Plano salvo = planoService.salvar(plano);
        return new ResponseEntity<>(PlanoDTO.fromEntity(salvo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        List<PlanoDTO> lista = planoService.listarTodos()
                .stream()
                .map(PlanoDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(@PathVariable Long id) {
        Plano plano = planoService.buscarPorId(id);
        return ResponseEntity.ok(PlanoDTO.fromEntity(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@PathVariable Long id, @RequestBody PlanoDTO dto) {
        Plano plano = planoService.buscarPorId(id);
        plano.setNome(dto.getNome());
        plano.setValorMensal(dto.getValorMensal());
        Plano atualizado = planoService.salvar(plano);
        return ResponseEntity.ok(PlanoDTO.fromEntity(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
