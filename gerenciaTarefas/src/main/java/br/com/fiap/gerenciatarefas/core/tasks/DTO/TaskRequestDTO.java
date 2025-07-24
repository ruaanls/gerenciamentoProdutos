package br.com.fiap.gerenciatarefas.core.tasks.DTO;

import br.com.fiap.gerenciatarefas.adapters.outbound.JPA.entities.UserJpa;
import br.com.fiap.gerenciatarefas.core.tasks.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TaskRequestDTO
{
    @NotBlank(message = "O campo titulo não deve ficar em branco")
    @NotNull(message = "O campo titulo é obrigatório")
    private String titulo;
    @NotBlank(message = "O campo descrição não deve ficar em branco")
    @NotNull(message = "O campo descrição é obrigatório")
    private String descricao;
    @NotBlank(message = "O campo prazo não deve ficar em branco")
    @NotNull(message = "O campo prazo é obrigatório")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "O campo prazo deve estar no formato dd/mm/yyyy")
    private String prazo;
    private StatusTask status;
    @NotBlank(message = "O campo username não deve ficar em branco")
    @NotNull(message = "O campo username é obrigatório")
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
