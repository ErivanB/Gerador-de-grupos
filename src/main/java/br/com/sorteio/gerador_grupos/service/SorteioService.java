package br.com.sorteio.gerador_grupos.service;

import br.com.sorteio.gerador_grupos.dao.AlunoDAO;
import br.com.sorteio.gerador_grupos.dao.GrupoDAO;
import br.com.sorteio.gerador_grupos.model.Aluno;
import br.com.sorteio.gerador_grupos.model.Grupo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service // Marca esta classe como um serviço do Spring (lógica de negócio)
public class SorteioService {

    @Autowired // Injeta nossa classe AlunoDAO para podermos acessar o banco
    private AlunoDAO alunoDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    @Transactional
    public List<Grupo> sortear(int numeroDeGrupos) {
        // 1. Busca todos os alunos do banco de dados
        List<Aluno> alunos = alunoDAO.listarTodos();

        if (alunos.isEmpty() || numeroDeGrupos <= 0) {
            return List.of(); // Retorna um mapa vazio se não houver alunos ou grupos
        }

        // 2. Embaralha a lista de alunos para garantir a aleatoriedade
        Collections.shuffle(alunos);

        // 3. Cria os grupos no banco
        List<Grupo> grupos = new ArrayList<>();
        for (int i = 1; i <= numeroDeGrupos; i++) {
            Grupo grupo = new Grupo();
            grupo.setNome("Grupo " + i);
            grupoDAO.salvar(grupo);
            grupos.add(grupo);
        }

        // 4. Distribui os alunos nos grupos
        int grupoAtual = 0;
        for (Aluno aluno : alunos) {
            Grupo grupo = grupos.get(grupoAtual);
            aluno.setGrupo(grupo);
            alunoDAO.salvar(aluno);

            grupoAtual = (grupoAtual + 1) % numeroDeGrupos; // alterna entre os grupos
        }

        // 5. Retorna todos os grupos com seus respectivos alunos
        return grupoDAO.listarTodos();
    }
}
