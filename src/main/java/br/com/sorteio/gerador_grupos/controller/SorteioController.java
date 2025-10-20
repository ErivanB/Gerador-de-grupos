package br.com.sorteio.gerador_grupos.controller;

import br.com.sorteio.gerador_grupos.dao.AlunoDAO;
import br.com.sorteio.gerador_grupos.dao.GrupoDAO;
import br.com.sorteio.gerador_grupos.model.Aluno;
import br.com.sorteio.gerador_grupos.model.Grupo;
import br.com.sorteio.gerador_grupos.service.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Define que esta classe é um controlador REST
@RequestMapping("/api") // Todas as URLs desta classe começarão com /api
public class SorteioController {

    @Autowired
    private AlunoDAO alunoDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private SorteioService sorteioService;

    // === ALUNO ===

    // Endpoint para adicionar um novo aluno
    // Ex: POST http://localhost:8080/api/alunos
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> adicionarAluno(@RequestBody Aluno aluno) {
        alunoDAO.salvar(aluno);
        return ResponseEntity.ok(aluno);
    }

    // Endpoint para listar todos os alunos cadastrados
    // Ex: GET http://localhost:8080/api/alunos
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoDAO.listarTodos();
        return ResponseEntity.ok(alunos);
    }

    // === GRUPOS ===
    @PostMapping("/grupos")
    public ResponseEntity<Grupo> salvarGrupo(@RequestBody Grupo grupo) {
        grupoDAO.salvar(grupo);
        return ResponseEntity.ok(grupo);
    }

    // Listar todos os grupos (com alunos)
    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> listarGrupos() {
        List<Grupo> grupos = grupoDAO.listarTodos();
        return ResponseEntity.ok(grupos);
    }

    // === SORTEIO ===

    // Realizar o sorteio e salvar no banco
    // Endpoint para realizar o sorteio
    // Ex: GET http://localhost:8080/api/sorteio?grupos=3
    @GetMapping("/sorteio")
    public ResponseEntity<List<Grupo>> realizarSorteio(@RequestParam("grupos") int numeroDeGrupos) {
        List<Grupo> gruposSorteados = sorteioService.sortear(numeroDeGrupos);
        return ResponseEntity.ok(gruposSorteados);
    }
}
