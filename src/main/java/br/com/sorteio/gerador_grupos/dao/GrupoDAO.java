package br.com.sorteio.gerador_grupos.dao;

import br.com.sorteio.gerador_grupos.model.Grupo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GrupoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void salvar(Grupo grupo) {
        entityManager.persist(grupo);
    }

    public List<Grupo> listarTodos() {
        return entityManager.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
    }
}
