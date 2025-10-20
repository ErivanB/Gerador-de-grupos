package br.com.sorteio.gerador_grupos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity // 1. Diz ao JPA que esta classe é uma tabela no banco de dados
@Table(name = "alunos") // 2. Opcional: Define o nome da tabela. Se não usar, será "aluno"
public class Aluno {

    @Id // 3. Define que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. O banco de dados vai gerar o ID automaticamente (auto-incremento)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    @JsonIgnoreProperties("alunos")
    private Grupo grupo;

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

    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
