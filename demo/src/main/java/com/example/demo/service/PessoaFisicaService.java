package com.example.demo.service;

import com.example.demo.model.PessoaFisica;
import com.example.demo.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService implements UsuarioService<PessoaFisica> {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public List<PessoaFisica> listarTodos() {
        return pessoaFisicaRepository.findAll();
    }

    @Override
    public PessoaFisica criar(PessoaFisica pessoaFisica) {
        if (existeEmail(pessoaFisica.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }
        if (existeIdentificador(pessoaFisica.getCpf())) {
            throw new IllegalArgumentException("Já existe um usuário com este CPF.");
        }
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public Optional<PessoaFisica> autenticar(String cpf, String senha) {
        // Implementação de autenticação
        return pessoaFisicaRepository.findByCpfAndSenha(cpf, senha);
    }

    @Override
    public PessoaFisica atualizar(Long id, PessoaFisica detalhesPessoaFisica) {
        // Implementação de atualização
        return pessoaFisicaRepository.findById(id)
                .map(pessoaFisica -> {
                    // Atualizar campos da pessoaFisica
                    return pessoaFisicaRepository.save(pessoaFisica);
                }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    @Override
    public boolean deletar(Long id) {
        if (pessoaFisicaRepository.existsById(id)) {
            pessoaFisicaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<PessoaFisica> buscaPorId(Long id) {
        return pessoaFisicaRepository.findById(id);
    }

    @Override
    public boolean existeEmail(String email) {
        return pessoaFisicaRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existeIdentificador(String cpf) {
        return pessoaFisicaRepository.findByCpf(cpf).isPresent();
    }
}