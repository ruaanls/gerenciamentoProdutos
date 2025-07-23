package br.com.fiap.gerenciatarefas.core.tasks;

public enum StatusTask
{
    COMPLETO("Completo"),
    EMANDAMENTO("Em andamento"),
    INCOMPLETA("Incompleta");

    private String status;

    StatusTask(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
