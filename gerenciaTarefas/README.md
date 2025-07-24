# Gerenciamento de Tarefas

Sistema completo para gerenciamento de tarefas, com autenticação, controle de usuários e operações CRUD para tarefas. Desenvolvido em Java 21 com Spring Boot, Oracle Database e JWT para autenticação.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Oracle Database
- MapStruct
- Lombok

## Configuração

1. **Banco de Dados:**
   - O sistema utiliza Oracle Database. Configure as variáveis de ambiente `DB_USERNAME` e `DB_PASSWORD`.
   - Exemplo de configuração em `application.properties`:
     ```properties
     spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     api.security.token.secret=${JWT_SECRET:my-secret-key}
     spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
     ```

2. **Build:**
   - Compile o projeto com:
     ```bash
     ./gradlew clean build
     ```

3. **Execução:**
   - Rode a aplicação:
     ```bash
     ./gradlew bootRun
     ```

## Endpoints

### Autenticação

#### POST `/auth/login`
- **Descrição:** Realiza login e retorna um token JWT.
- **Payload:**
  ```json
  {
    "login": "usuario",
    "password": "senha"
  }
  ```
- **Resposta:**
  ```json
  {
    "token": "<jwt_token>"
  }
  ```

#### POST `/auth/register`
- **Descrição:** Registra um novo usuário (apenas ADMIN pode registrar).
- **Payload:**
  ```json
  {
    "login": "usuario",
    "password": "senha",
    "user_role": "ADMIN" // ou "USER"
  }
  ```

### Tarefas

#### POST `/tasks/create`
- **Descrição:** Cria uma nova tarefa (apenas ADMIN).
- **Payload:**
  ```json
  {
    "titulo": "Título da tarefa",
    "descricao": "Descrição detalhada",
    "prazo": "2024-08-01",
    "status": "EMANDAMENTO", // ou "COMPLETO", "INCOMPLETA"
    "username": "usuario"
  }
  ```

#### GET `/tasks/{id}`
- **Descrição:** Busca uma tarefa pelo ID.
- **Resposta:**
  ```json
  {
    "id": 1,
    "titulo": "Título da tarefa",
    "descricao": "Descrição detalhada",
    "prazo": "2024-08-01",
    "status": "EMANDAMENTO",
    "autor": "usuario"
  }
  ```

#### PUT `/tasks/{id}`
- **Descrição:** Atualiza uma tarefa pelo ID (apenas ADMIN).
- **Payload:** Igual ao de criação.

#### DELETE `/tasks/{id}`
- **Descrição:** Remove uma tarefa pelo ID (apenas ADMIN).

#### GET `/tasks/all/{username}?page=0`
- **Descrição:** Lista todas as tarefas de um usuário paginadas.
- **Resposta:**
  ```json
  {
    "content": [
      {
        "id": 1,
        "titulo": "Título da tarefa",
        "descricao": "Descrição detalhada",
        "prazo": "2024-08-01",
        "status": "EMANDAMENTO",
        "autor": "usuario"
      }
    ],
    "totalPages": 1,
    "totalElements": 1,
    "size": 10,
    "number": 0
  }
  ```

## Perfis de Usuário
- **ADMIN:** Pode registrar usuários, criar, editar e remover tarefas.
- **USER:** Pode visualizar suas tarefas.

## Observações
- O sistema utiliza autenticação JWT. Inclua o token no header `Authorization: Bearer <token>` para acessar endpoints protegidos.
- Os valores possíveis para `user_role` são: `ADMIN`, `USER`.
- Os valores possíveis para `status` são: `COMPLETO`, `EMANDAMENTO`, `INCOMPLETA`.

---

Desenvolvido para fins acadêmicos na FIAP. 