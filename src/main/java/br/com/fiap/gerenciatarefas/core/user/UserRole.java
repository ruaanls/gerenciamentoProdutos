package br.com.fiap.gerenciatarefas.core.user;

public enum UserRole
{
    ADMIN("Admin"),
    USER("User");

    private String role;

    private UserRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
}
