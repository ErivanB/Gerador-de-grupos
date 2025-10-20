package br.com.sorteio.gerador_grupos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // 1. Diz ao JPA que esta classe é uma tabela no banco de dados
@Table(name = "alunos") // 2. Opcional: Define o nome da tabela. Se não usar, será "aluno"
public class Aluno {

    @Id // 3. Define que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. O banco de dados vai gerar o ID automaticamente (auto-incremento)
    private Long id;

    private String nome;

    // Construtores
    public Aluno() {
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
