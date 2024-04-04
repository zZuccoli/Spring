package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaJuridica extends Usuario {
    @NotNull
    @Size(min = 2, max = 100)
    private String razaoSocial;

    @NotNull
    @Size(min = 14, max = 14, message = "O CNPJ deve conter exatamente 14 dígitos.")
    private String cnpj;

    @NotNull
    private String areaDeAtuacao;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(String senha, String email, String razaoSocial, String cnpj, String areaDeAtuacao) {
        super(senha, email);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }
}

