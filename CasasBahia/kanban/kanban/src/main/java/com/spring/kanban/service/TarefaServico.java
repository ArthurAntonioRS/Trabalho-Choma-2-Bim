package com.spring.kanban.service;

import com.spring.kanban.enums.TarefaStatus;
import com.spring.kanban.model.Tarefa;
import com.spring.kanban.repository.TarefaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class TarefaServico {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    public List<Tarefa> listarTarefas() {
        return tarefaRepositorio.findAll();
    }



    public Map<TarefaStatus.StatusTarefa, List<Tarefa>> listarTarefasOrdenadasPorColuna() {
        Map<TarefaStatus.StatusTarefa, List<Tarefa>> tarefasAgrupadas = new LinkedHashMap<>();

        // Inserindo as colunas na ordem desejada
        tarefasAgrupadas.put(TarefaStatus.StatusTarefa.A_FAZER, tarefaRepositorio.findByStatusOrderByPrioridadeAsc(TarefaStatus.StatusTarefa.A_FAZER));
        tarefasAgrupadas.put(TarefaStatus.StatusTarefa.EM_PROGRESSO, tarefaRepositorio.findByStatusOrderByPrioridadeAsc(TarefaStatus.StatusTarefa.EM_PROGRESSO));
        tarefasAgrupadas.put(TarefaStatus.StatusTarefa.CONCLUIDO, tarefaRepositorio.findByStatusOrderByPrioridadeAsc(TarefaStatus.StatusTarefa.CONCLUIDO));

        return tarefasAgrupadas;
    }


    public List<Tarefa> filtrarTarefasPorPrioridadeEDataLimite(TarefaStatus.Prioridade prioridade, LocalDate dataLimite) {
        return tarefaRepositorio.findByPrioridadeAndDataLimite(prioridade, dataLimite);
    }


    public Map<TarefaStatus.StatusTarefa, List<Tarefa>> gerarRelatorioTarefasAtrasadas() {
        Map<TarefaStatus.StatusTarefa, List<Tarefa>> relatorio = listarTarefasOrdenadasPorColuna();
        LocalDate agora = LocalDate.now();

        for (Map.Entry<TarefaStatus.StatusTarefa, List<Tarefa>> entry : relatorio.entrySet()) {
            List<Tarefa> tarefas = entry.getValue();
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getDataLimite() != null && tarefa.getDataLimite().isBefore(agora) && tarefa.getStatus() != TarefaStatus.StatusTarefa.CONCLUIDO) {
                    tarefa.setDescricao(tarefa.getDescricao() + " (Atrasada)"); // Marcar como atrasada na descrição
                } else {
                System.out.println("Sem atrasos");
                }
            }
        }
        return relatorio;
    }



    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setStatus(TarefaStatus.StatusTarefa.A_FAZER);
        // Verifica se a data limite foi fornecida na criação
        if (tarefa.getDataLimite() == null) {
            tarefa.setDataLimite(null); // Define como null se não foi fornecida
        }
        return tarefaRepositorio.save(tarefa);
    }

    public Tarefa moverTarefa(int tarefaId) {
        Optional<Tarefa> tarefaOptional = tarefaRepositorio.findById(tarefaId);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();
            if (tarefa.getStatus() == TarefaStatus.StatusTarefa.A_FAZER) {
                tarefa.setStatus(TarefaStatus.StatusTarefa.EM_PROGRESSO);
            } else if (tarefa.getStatus() == TarefaStatus.StatusTarefa.EM_PROGRESSO) {
                tarefa.setStatus(TarefaStatus.StatusTarefa.CONCLUIDO);
            }
            return tarefaRepositorio.save(tarefa);
        }
        throw new RuntimeException("Tarefa não encontrada");
    }

    public Tarefa editarTarefa(int tarefaId, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaOptional = tarefaRepositorio.findById(tarefaId);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();

            if (tarefaAtualizada.getTitulo() != null) {
                tarefa.setTitulo(tarefaAtualizada.getTitulo());
            }
            if (tarefaAtualizada.getDescricao() != null) {
                tarefa.setDescricao(tarefaAtualizada.getDescricao());
            }
            if (tarefaAtualizada.getPrioridade() != null) {
                tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
            }
            if (tarefaAtualizada.getDataLimite() != null) {
                tarefa.setDataLimite(tarefaAtualizada.getDataLimite());
            }

            return tarefaRepositorio.save(tarefa);
        }
        throw new RuntimeException("Tarefa não encontrada");
    }

    public void deletarTarefa(int tarefaId) {
        tarefaRepositorio.deleteById(tarefaId);
    }
}