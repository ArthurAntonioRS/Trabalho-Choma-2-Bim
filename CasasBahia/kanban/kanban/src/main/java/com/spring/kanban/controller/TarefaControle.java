package com.spring.kanban.controller;

import com.spring.kanban.enums.TarefaStatus;
import com.spring.kanban.model.Tarefa;
import com.spring.kanban.service.TarefaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kanban")
public class TarefaControle {

    @Autowired
    private TarefaServico tarefaServico;

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaServico.listarTarefas();
    }



    @GetMapping("/tarefas-ordenadas")
    public Map<TarefaStatus.StatusTarefa, List<Tarefa>> listarTarefasOrdenadasPorColuna() {
        return tarefaServico.listarTarefasOrdenadasPorColuna();
    }


    @GetMapping("/tarefas-filtro")
    public List<Tarefa> filtrarTarefas(
            @RequestParam TarefaStatus.Prioridade prioridade,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataLimite) {
        return tarefaServico.filtrarTarefasPorPrioridadeEDataLimite(prioridade, dataLimite);
    }


    @GetMapping("/relatorio-tarefas")
    public Map<TarefaStatus.StatusTarefa, List<Tarefa>> gerarRelatorioTarefasAtrasadas() {
        return tarefaServico.gerarRelatorioTarefasAtrasadas();
    }



    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaServico.criarTarefa(tarefa);
    }

    @PutMapping("/{id}/move")
    public Tarefa moverTarefa(@PathVariable int id) {
        return tarefaServico.moverTarefa(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable int id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaServico.editarTarefa(id, tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable int id) {
        tarefaServico.deletarTarefa(id);
    }
}