package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface UsuarioService<T> {
    List<T> listarTodos();
    T criar(T entidade);
    Optional<T> autenticar(String identificador, String senha);
    T atualizar(Long id, T detalhesEntidade);
    boolean deletar(Long id);
    Optional<T> buscaPorId(Long id);
    boolean existeEmail(String email);
    boolean existeIdentificador(String identificador);
    T atualizarporidentificador (String identificador, T detalhesEntidade);
}
