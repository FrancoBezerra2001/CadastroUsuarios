# API de Cadastro com Sessão Temporária (Spring Boot)

Este projeto é uma API REST desenvolvida em **Java** com **Spring Boot** para o cadastro de usuários, implementando uma regra de negócio específica: ao realizar o primeiro login, o usuário recebe acesso a um endpoint restrito por exatamente **1 minuto**. Após esse tempo, o acesso é automaticamente bloqueado.

## 🚀 Funcionalidades

*   **Cadastro de Usuário**: Recebe dados via JSON e armazena no banco de dados.
*   **Autenticação**: Validação de credenciais para liberação de acesso.
*   **Controle de Sessão**: Lógica customizada para expiração de acesso após 60 segundos do primeiro login.
*   **Proteção de Endpoint**: Bloqueio de acesso para usuários não autenticados ou com sessão expirada.

## 🛠️ Tecnologias Utilizadas

*   **Java 17** (ou superior)
*   **Spring Boot 3.x**
*   **Spring Data JPA**: Para persistência de dados.
*   **Spring Security**: Para controle de autenticação e segurança.
*   **MySQL / H2**: Banco de dados relacional.
*   **Maven**: Gerenciador de dependências.

## 📂 Estrutura de Pastas
```text
src/main/java/com/example/usuarios/
├── config/       # Configurações de segurança e lógica de sessão
├── controller/   # Endpoints da API (Cadastro, Login e Acesso)
├── model/        # Entidades e DTOs
├── repository/   # Interfaces de comunicação com o banco
└── service/      # Regras de negócio e validações de tempo
