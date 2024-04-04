package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaFisica extends Usuario {

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    @Size(min = 2, max = 100)
    private String sobrenome;

    @NotNull
    @Min(18)
    private int idade;

    @NotNull
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos.")
    private String cpf;

    @NotNull
    private char sexo;


    public PessoaFisica() {
        super();
    }

    public PessoaFisica(String senha, String email, String nome, String sobrenome, int idade, String cpf, char sexo) {
        super(senha, email);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
    }


    // Getters
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public char getSexo() {
        return sexo;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
