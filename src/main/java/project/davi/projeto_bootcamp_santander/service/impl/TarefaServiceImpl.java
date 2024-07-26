package project.davi.projeto_bootcamp_santander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.davi.projeto_bootcamp_santander.model.Responsavel;
import project.davi.projeto_bootcamp_santander.model.Tarefa;
import project.davi.projeto_bootcamp_santander.repository.ResponsavelRepository;
import project.davi.projeto_bootcamp_santander.repository.TarefaRepository;
import project.davi.projeto_bootcamp_santander.service.TarefaService;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Override
    public Tarefa criarTarefa(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("A tarefa não pode ser nula");
        }
        Responsavel responsavel = tarefa.getResponsavel();
        if (responsavel != null) {
            Responsavel responsavelExistente = responsavelRepository.findByNome(responsavel.getNome());
            if (responsavelExistente != null) {
                tarefa.setResponsavel(responsavelExistente);
            } else {
                responsavel = responsavelRepository.save(responsavel);
                tarefa.setResponsavel(responsavel);
            }
        }
        return tarefaRepository.save(tarefa);
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    @Override
    public Optional<Tarefa> obterTarefa(Integer id) {
        return tarefaRepository.findById(id);
    }

    @Override
    public Tarefa atualizarTarefa(Integer id, Tarefa tarefaAtualizada) {
        if (tarefaAtualizada == null) {
            throw new IllegalArgumentException("A tarefa a ser atualizada não pode ser nula");
        }
        if (!tarefaRepository.existsById(id)) {
            return null;
        }
        tarefaAtualizada.setId(id);
        return tarefaRepository.save(tarefaAtualizada);
    }

    @Override
    public boolean deletarTarefa(Integer id) {
        if (!tarefaRepository.existsById(id)) {
            return false;
        }
        tarefaRepository.deleteById(id);
        return true;
    }
}

