package com.exemplo.usuarios.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.usuarios.config.SessaoManager;
import com.exemplo.usuarios.model.Usuario;
import com.exemplo.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;
    private final SessaoManager sessaoManager;

    // Injeção de dependência via construtor (recomendado)
    public UsuarioController(UsuarioService service, SessaoManager sessaoManager) {
        this.service = service;
        this.sessaoManager = sessaoManager;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@Valid @RequestBody Usuario usuario) {
        try {
            // Verifica se usuário já existe
            if (service.buscarPorUsername(usuario.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Usuário já cadastrado!");
            }
            
            service.salvar(usuario);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Usuario usuario) {
        try {
            // Validação de entrada
            if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
                return ResponseEntity.badRequest().body("Username é obrigatório!");
            }
            
            if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Password é obrigatório!");
            }

            Optional<Usuario> user = service.buscarPorUsername(usuario.getUsername());

            if (user.isPresent() && user.get().getPassword().equals(usuario.getPassword())) {
                sessaoManager.criarSessao(usuario.getUsername());
                return ResponseEntity.ok("Login realizado! Acesso liberado por 1 minuto.");
            }

            return ResponseEntity.status(401).body("Usuário ou senha inválidos!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no processo de login: " + e.getMessage());
        }
    }

    @GetMapping("/acesso")
    public ResponseEntity<String> acesso(@RequestParam String username) {
        try {
            // Validação de entrada
            if (username == null || username.isEmpty()) {
                return ResponseEntity.badRequest().body("Username é obrigatório!");
            }
            
            if (sessaoManager.sessaoValida(username)) {
                return ResponseEntity.ok("Acesso garantido!");
            }
            return ResponseEntity.status(401).body("Acesso negado. Você não fez login ou a sessão expirou.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao verificar acesso: " + e.getMessage());
        }
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("API funcionando!");
    }

    // Getters removidos pois não são necessários para dependências injetadas via construtor
}
