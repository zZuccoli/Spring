package com.example.demo.controller;

import com.example.demo.model.PessoaFisica;
import com.example.demo.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoasFisicas")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping
    public List<PessoaFisica> listarTodas() {
        return pessoaFisicaService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PessoaFisica pessoaFisica) {
        try {
            PessoaFisica criada = pessoaFisicaService.criar(pessoaFisica);
            return new ResponseEntity<>(criada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisica> buscarPorId(@PathVariable Long id) {
        Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.buscaPorId(id);
        return pessoaFisica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisica detalhesPessoaFisica) {
        PessoaFisica atualizada = pessoaFisicaService.atualizar(id, detalhesPessoaFisica);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (pessoaFisicaService.deletar(id)) {
            return ResponseEntity.ok().body("Cadastro de pessoa Fisica deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PessoaFisica pessoaFisica) {
        Optional<PessoaFisica> usuarioAutenticado = pessoaFisicaService.autenticar(pessoaFisica.getCpf(), pessoaFisica.getSenha());
        if (usuarioAutenticado.isPresent()) {
            return ResponseEntity.ok().body("Login bem sucedido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos");
        }
    }
}
