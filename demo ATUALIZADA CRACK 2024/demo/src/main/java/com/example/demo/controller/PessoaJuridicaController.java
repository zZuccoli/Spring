package com.example.demo.controller;

import com.example.demo.model.PessoaJuridica;
import com.example.demo.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoasJuridicas") // Atualize o mapeamento conforme necessário
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public List<PessoaJuridica> listarTodas() {
        return pessoaJuridicaService.listarTodos();
    }

    @PostMapping("/registro")
    public ResponseEntity<?> criar(@Valid @RequestBody PessoaJuridica pessoaJuridica) {
        try {
            PessoaJuridica criada = pessoaJuridicaService.criar(pessoaJuridica);
            return new ResponseEntity<>(criada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridica> buscarPorId(@PathVariable Long id) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.buscaPorId(id);
        return pessoaJuridica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/cnpj/{cnpj}")
    public ResponseEntity<PessoaJuridica> atualizarPorCnpj(@PathVariable String cnpj, @Valid @RequestBody PessoaJuridica detalhesPessoaJuridica) {
        PessoaJuridica atualizada = pessoaJuridicaService.atualizarporidentificador(cnpj, detalhesPessoaJuridica);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PessoaJuridica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaJuridica detalhesPessoaJuridica) {
        PessoaJuridica atualizada = pessoaJuridicaService.atualizar(id, detalhesPessoaJuridica);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (pessoaJuridicaService.deletar(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PessoaJuridica pessoaJuridica) {
        Optional<PessoaJuridica> usuarioAutenticado = pessoaJuridicaService.autenticar(pessoaJuridica.getCnpj(), pessoaJuridica.getSenha());
        if (usuarioAutenticado.isPresent()) {
            return ResponseEntity.ok().body("Login bem sucedido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CNPJ ou senha incorretos");
        }
    }
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<PessoaJuridica> buscarPorCnpj(@PathVariable String cnpj) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.buscaPorCnpj(cnpj);
        return pessoaJuridica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity<String> deletarPorCnpj(@PathVariable String cnpj) {
        boolean deleted = pessoaJuridicaService.deletarPorCnpj(cnpj);
        if (deleted) {
            return ResponseEntity.ok().body("Cadastro de pessoa jurídica deletado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
