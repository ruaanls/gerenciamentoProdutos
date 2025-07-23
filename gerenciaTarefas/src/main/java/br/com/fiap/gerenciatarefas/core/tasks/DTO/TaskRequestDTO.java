package br.com.fiap.gerenciatarefas.core.tasks.DTO;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.core.tasks.StatusTask;

public class TaskRequestDTO
{
    private String titulo;
    private String descricao;
    private String prazo;
    private StatusTask status;
    private String username;

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

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
