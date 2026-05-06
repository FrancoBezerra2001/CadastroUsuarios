package com.exemplo.usuarios.config;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessaoManager {

    private Map<String, LocalDateTime> sessoes = new HashMap<>();

    public void criarSessao(String username) {
        sessoes.put(username, LocalDateTime.now().plusMinutes(1));
    }

    public boolean sessaoValida(String username) {
        LocalDateTime expiracao = sessoes.get(username);
        return expiracao != null && expiracao.isAfter(LocalDateTime.now());
    }

    public void removerSessao(String username) {
        sessoes.remove(username);
    }
}