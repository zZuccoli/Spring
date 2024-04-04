package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A senha não pode ser nula.")
    @Size(min = 10, max = 100, message = "A senha deve ter entre 10 e 100 caracteres.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()]).{10,}", message = "A senha deve conter pelo menos um dígito, uma letra maiúscula, uma letra minúscula e um caractere especial.")
    private String senha;

    @NotNull(message = "O email não pode ser nulo.")
    @Size(min = 10, max = 100, message = "O email deve ter entre 10 e 100 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "O email deve ser um endereço de email válido.")
    private String email;

    // Construtor
    public Usuario() {
    }

    public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }

    // Getters and setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }

}