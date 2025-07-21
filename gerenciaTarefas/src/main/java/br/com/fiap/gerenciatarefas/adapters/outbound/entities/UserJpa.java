package br.com.fiap.gerenciatarefas.adapters.outbound.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class UserJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
