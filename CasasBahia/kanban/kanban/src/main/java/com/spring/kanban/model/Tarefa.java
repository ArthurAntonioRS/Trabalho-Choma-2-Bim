package com.spring.kanban.model;

import com.spring.kanban.enums.TarefaStatus;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private TarefaStatus.StatusTarefa status = TarefaStatus.StatusTarefa.A_FAZER;

    @Enumerated(EnumType.STRING)
    private TarefaStatus.Prioridade prioridade = TarefaStatus.Prioridade.BAIXA;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataLimite;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
    public TarefaStatus.StatusTarefa getStatus() { return status; }
    public void setStatus(TarefaStatus.StatusTarefa status) { this.status = status; }
    public TarefaStatus.Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(TarefaStatus.Prioridade prioridade) { this.prioridade = prioridade; }
    public LocalDate getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDate dataLimite) { this.dataLimite = dataLimite; }
}