package br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities;

import br.com.fiap.gerenciatarefas.core.tasks.StatusTask;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class TasksJPA
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTask status;
    private String prazo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpa userId;

    public TasksJPA(Long id, String titulo, String descricao, StatusTask status, String prazo, UserJpa userId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prazo = prazo;
        this.userId = userId;
    }

    public TasksJPA() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public UserJpa getUserId() {
        return userId;
    }

    public void setUserId(UserJpa userId) {
        this.userId = userId;
    }
}
