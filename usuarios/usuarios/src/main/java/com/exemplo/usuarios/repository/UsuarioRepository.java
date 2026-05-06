package com.exemplo.usuarios.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Você também vai precisar deste método para buscar o usuário pelo nome no login:
    Usuario findByUsername(String username);
}