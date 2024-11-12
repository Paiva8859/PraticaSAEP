package com.example.pratica_saep.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pratica_saep.Model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
