package com.example.demo.service;

import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService implements UsuarioService<PessoaJuridica> {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    public List<PessoaJuridica> listarTodos() {
        return pessoaJuridicaRepository.findAll();
    }

    @Override
    public PessoaJuridica criar(PessoaJuridica pessoaJuridica) {
        if (existeEmail(pessoaJuridica.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }
        if (existeIdentificador(pessoaJuridica.getCnpj())) {
            throw new IllegalArgumentException("Já existe um usuário com este CNPJ.");
        }
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }
    @Override
    public Optional<PessoaJuridica> autenticar(String cnpj, String senha) {
        return pessoaJuridicaRepository.findByCnpjAndSenha(cnpj, senha);
    }

    @Override
    public PessoaJuridica atualizar(Long id, PessoaJuridica detalhesPessoaJuridica) {
        return pessoaJuridicaRepository.findById(id).map(pessoaJuridica -> {
            pessoaJuridica.setRazaoSocial(detalhesPessoaJuridica.getRazaoSocial());
            pessoaJuridica.setCnpj(detalhesPessoaJuridica.getCnpj());
            pessoaJuridica.setAreaDeAtuacao(detalhesPessoaJuridica.getAreaDeAtuacao());
            pessoaJuridica.setSenha(detalhesPessoaJuridica.getSenha());
            return pessoaJuridicaRepository.save(pessoaJuridica);
        }).orElse(null); // Considere lançar uma exceção ou uma abordagem diferente para null
    }

    @Override
    public PessoaJuridica atualizarporidentificador(String cnpj, PessoaJuridica detalhesPessoaJuridica) {
        return pessoaJuridicaRepository.findByCnpj(cnpj)
                .map(pessoaJuridica -> {
                    pessoaJuridica.setRazaoSocial(detalhesPessoaJuridica.getRazaoSocial());
                    pessoaJuridica.setCnpj(detalhesPessoaJuridica.getCnpj());
                    pessoaJuridica.setAreaDeAtuacao(detalhesPessoaJuridica.getAreaDeAtuacao());
                    // Atualize outros campos conforme necessário
                    return pessoaJuridicaRepository.save(pessoaJuridica);
                })
                .orElseThrow(() -> new IllegalArgumentException("Usuário com CNPJ não encontrado."));
    }
    @Override
    public boolean deletar(Long id) {
        if (pessoaJuridicaRepository.existsById(id)) {
            pessoaJuridicaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<PessoaJuridica> buscaPorId(Long id) {
        return pessoaJuridicaRepository.findById(id);
    }

    @Override
    public boolean existeEmail(String email) {
        return pessoaJuridicaRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existeIdentificador(String cnpj) {
        return pessoaJuridicaRepository.findByCnpj(cnpj).isPresent();
    }

    public Optional<PessoaJuridica> buscaPorCnpj(String cnpj) {
        return pessoaJuridicaRepository.findByCnpj(cnpj);
    }

    public boolean deletarPorCnpj(String cnpj) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findByCnpj(cnpj);
        if (pessoaJuridica.isPresent()) {
            pessoaJuridicaRepository.delete(pessoaJuridica.get());
            return true;
        }
        return false;
    }
}
