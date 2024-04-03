package com.example.demo.repository;

import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    Optional<PessoaJuridica> findByCnpjAndSenha(String cnpj, String senha);
    Optional<PessoaJuridica> findByEmail(String email);
    Optional<PessoaJuridica> findByCnpj(String Cnpj);
}
