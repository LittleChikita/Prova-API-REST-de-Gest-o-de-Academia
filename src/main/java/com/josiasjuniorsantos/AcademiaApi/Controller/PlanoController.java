package com.josiasjuniorsantos.AcademiaApi.Controller;

import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planos")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @PostMapping
    public Plano criarPlano(@RequestBody Plano plano) {
        return service.salvar(plano);
    }

    @GetMapping
    public List<Plano> listarPlanos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPlanoPorId(@PathVariable Long id) {
        Plano plano = service.buscarPorId(id);
        return ResponseEntity.ok(plano);
    }


    @PutMapping("/{id}")
    public Plano atualizarPlano(@PathVariable Long id, @RequestBody Plano planoAtualizado) {
        Plano planoExistente = service.buscarPorId(id);
        planoExistente.setNome(planoAtualizado.getNome());
        planoExistente.setValorMensal(planoAtualizado.getValorMensal());
        return service.salvar(planoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        service.buscarPorId(id);
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
