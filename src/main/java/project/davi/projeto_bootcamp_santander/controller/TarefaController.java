package project.davi.projeto_bootcamp_santander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.davi.projeto_bootcamp_santander.model.Tarefa;
import project.davi.projeto_bootcamp_santander.repository.TarefaRepository;
import project.davi.projeto_bootcamp_santander.service.TarefaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        if (tarefa == null) {
            return ResponseEntity.badRequest().build();
        }
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaTarefa.getId())
                .toUri();
        return ResponseEntity.created(location).body(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterTarefa(@PathVariable Integer id) {
        Optional<Tarefa> tarefa = tarefaService.obterTarefa(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefaAtualizada) {
        if (tarefaAtualizada == null) {
            return ResponseEntity.badRequest().build();
        }
        Tarefa tarefa = tarefaService.atualizarTarefa(id, tarefaAtualizada);
        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Integer id) {
        if (!tarefaService.deletarTarefa(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

