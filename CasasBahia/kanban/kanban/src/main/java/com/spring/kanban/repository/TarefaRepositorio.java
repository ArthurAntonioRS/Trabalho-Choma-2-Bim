package com.spring.kanban.repository;

import com.spring.kanban.enums.TarefaStatus;
import com.spring.kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import com.spring.kanban.enums.TarefaStatus.StatusTarefa;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByStatusOrderByPrioridadeAsc(StatusTarefa status);
    List<Tarefa> findByPrioridadeAndDataLimite(TarefaStatus.Prioridade prioridade, LocalDate dataLimite);
}