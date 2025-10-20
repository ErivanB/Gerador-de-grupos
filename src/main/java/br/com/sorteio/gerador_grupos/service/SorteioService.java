package br.com.sorteio.gerador_grupos.service;



import br.com.sorteio.gerador_grupos.dao.AlunoDAO;
import br.com.sorteio.gerador_grupos.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // Marca esta classe como um serviço do Spring (lógica de negócio)
public class SorteioService {

    @Autowired // Injeta nossa classe AlunoDAO para podermos acessar o banco
    private AlunoDAO alunoDAO;

    public Map<Integer, List<String>> sortear(int numeroDeGrupos) {
        // 1. Busca todos os alunos do banco de dados
        List<Aluno> todosOsAlunos = alunoDAO.listarTodos();

        if (todosOsAlunos.isEmpty() || numeroDeGrupos <= 0) {
            return Collections.emptyMap(); // Retorna um mapa vazio se não houver alunos ou grupos
        }

        // 2. Embaralha a lista de alunos para garantir a aleatoriedade
        Collections.shuffle(todosOsAlunos);

        // 3. Prepara a estrutura para guardar os grupos
        Map<Integer, List<String>> grupos = new HashMap<>();
        for (int i = 1; i <= numeroDeGrupos; i++) {
            grupos.put(i, new ArrayList<>());
        }

        // 4. Distribui os alunos nos grupos
        int grupoAtual = 1;
        for (Aluno aluno : todosOsAlunos) {
            grupos.get(grupoAtual).add(aluno.getNome());

            grupoAtual++;
            if (grupoAtual > numeroDeGrupos) {
                grupoAtual = 1; // Volta para o primeiro grupo
            }
        }

        return grupos;
    }
}
