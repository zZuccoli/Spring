package com.example.demo.service;

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
}
