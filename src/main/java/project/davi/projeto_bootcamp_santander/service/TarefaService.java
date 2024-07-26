package project.davi.projeto_bootcamp_santander.service;

import project.davi.projeto_bootcamp_santander.model.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaService {
    Tarefa criarTarefa(Tarefa tarefa);
    List<Tarefa> listarTarefas();
    Optional<Tarefa> obterTarefa(Integer id);
    Tarefa atualizarTarefa(Integer id, Tarefa tarefaAtualizada);
    boolean deletarTarefa(Integer id);
}
