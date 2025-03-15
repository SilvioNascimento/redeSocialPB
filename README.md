# Rede Social PB

---

## Sobre o projeto
Este projeto tem como propósito de simular uma rede social, o qual 
cada usuário pode criar, atualizar, listar e deletar tanto **posts**
quanto **comentários**.

---

## Estrutura de Pastas

### Exibindo toda a estrutura do projeto
```
├── .gitattributes
├── .gitignore
├── .mvn
└── wrapper
│   └── maven-wrapper.properties
├── LICENSE
├── diagrams
└── Diagrama Entidade-Relacionamento
│   ├── DER_de_redeSocialPB.brM3
│   └── DER_de_redeSocialPB.png
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
├── main
├── java
│   └── br
│   │   └── com
│   │       └── redeSocialPB
│   │           ├── RedeSocialPbApplication.java
│   │           ├── config
│   │               └── AdminUserSeeder.java
│   │           ├── controllers
│   │               ├── AuthController.java
│   │               ├── CommentController.java
│   │               ├── PostController.java
│   │               └── UserController.java
│   │           ├── dto
│   │               ├── ChangePasswordRequestDTO.java
│   │               ├── CommentDTO.java
│   │               ├── LoginRequestDTO.java
│   │               ├── PostDTO.java
│   │               ├── RegisterRequestDTO.java
│   │               ├── TokenResponseDTO.java
│   │               ├── UserDTO.java
│   │               └── UserResponseDTO.java
│   │           ├── entities
│   │               ├── Comment.java
│   │               ├── Post.java
│   │               └── User.java
│   │           ├── enums
│   │               └── Roles.java
│   │           ├── exception
│   │               ├── CommentNotFoundException.java
│   │               ├── ErrorResponse.java
│   │               ├── GlobalExceptionHandler.java
│   │               ├── PostNotFoundException.java
│   │               ├── UserNotFoundException.java
│   │               └── UserWithUsernameAlreadyExistsException.java
│   │           ├── repositories
│   │               ├── CommentRepository.java
│   │               ├── PostRepository.java
│   │               └── UserRepository.java
│   │           ├── security
│   │               ├── CustomUserDetailsService.java
│   │               ├── JwtAuthenticationFilter.java
│   │               ├── JwtUtil.java
│   │               └── SecurityConfig.java
│   │           ├── services
│   │               ├── CommentService.java
│   │               ├── PostService.java
│   │               └── UserService.java
│   │           ├── swagger
│   │               └── SwaggerConfig.java
│   │           └── validation
│   │               ├── EmailsExistentes.java
│   │               └── EmailsExistentesValidator.java
└── resources
│   ├── application-dev.properties
│   ├── application-test.properties
│   └── application.properties
└── test
├── java
└── br
│   └── com
│       └── redeSocialPB
│           ├── RedeSocialPbApplicationTests.java
│           └── unit_tests
│               ├── controllers
│                   └── CommentControllerTest.java
│               ├── repositories
│                   ├── CommentRepositoryTest.java
│                   ├── PostRepositoryTest.java
│                   └── UserRepositoryTest.java
│               └── services
│                   └── CommentServiceTest.java
└── resources
└── application.properties
```

### Exibindo a estrutura principal e suas breves descrições
```text
/ src                       # Código-fonte principal da aplicação
    / config                # Possui classes responsáveis por configurar a aplicação
    / controllers           # Lida com as rotas definidas do projeto
    / dto                   # Responsável por possuir classes que transacionam dados 
    / entities              # Entidades do banco de dados PostgreSQL
    / enums                 # Enums usados no sistema
    / exception             # Lida com exceções e erros personalizados no projeto
    / repositories          # Interfaces que extendem JpaRepository para manipular e acessar os dados persistidos no projeto
    / security              # Responsável pela segurança do projeto
    / services              # Contém lógica de negócios do sistema
    / swagger               # Configuração do Swagger para gerar a documentação da API
    / validation            # Validações customizadas do sistema
/ test                      # Testes automatizados do sistema
/ documents                 # Possui as documentações do projeto como diagramas, por exemplo
```

---

## Tecnologias Utilizadas
| Tecnologia         | Descrição                                                                                                                                   |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| **Java**           | Linguagem principal utilizada para o desenvolvimento do sistema, responsável pela lógica de negócios e manipulação de dados.                |
| **Spring Boot**    | Framework que simplifica a configuração e o desenvolvimento da aplicação, oferecendo uma maneira rápida e eficiente de criar APIs RESTful.   |
| **Spring JPA**     | Utilizado para persistência de dados, simplificando a interação com bancos de dados relacionais e realizando operações CRUD.                 |
| **Spring Security**| Framework para garantir a segurança da aplicação, implementando autenticação e autorização com suporte a JWT.                              |
| **Model Mapper**   | Biblioteca para conversão entre objetos, como entidades e DTOs, facilitando o mapeamento entre diferentes camadas do sistema.                |
| **H2 Database**    | Banco de dados relacional em memória utilizado para testes e desenvolvimento.                                                               |
| **PostgreSQL**     | Banco de dados relacional utilizado na produção, oferecendo robustez e escalabilidade para dados persistentes.                              |
| **JWT**            | Utilizado para autenticação de usuários, garantindo segurança nas requisições de API.                                                       |
| **Spring Doc (Swagger)** | Utilizado para gerar automaticamente a documentação interativa da API, facilitando o entendimento e a integração da equipe de desenvolvimento. |
| **JUnit**          | Framework para testes unitários, garantindo que o código da aplicação funcione conforme o esperado.                                          |
| **Mockito**        | Biblioteca de mocking utilizada para simular dependências e testar a lógica da aplicação de forma isolada.                                    |

---

## Rodando Localmente
- Clone o repositório:
    ```bash
    git clone https://github.com/SilvioNascimento/redeSocialPB.git
    ```

- Instala todas as dependências
  ```bash
  mvn clean install
  ```

- Inicializa o servidor
  ```bash
  mvn spring-boot:run
  ```

<br/>

**OBSERVAÇÃO:** Para executar os dois últimos passos, é necessário
que sua máquina possua o Maven instalado.

---

## Funcionalidades
- **Registrar Usuário**: Permite o registro de um novo usuário via POST.
- **Logar Usuário**: Permite o login de um usuário existente via POST.
- **Alterar Senha do Usuário**: Altera a senha de um usuário existente via POST.
- **Listar Usuários**: Retorna todos os usuários existentes via GET.
- **Exibir Usuário**: Retorna dados de um usuário existente via GET.
- **Atualizar Usuário**: Atualiza detalhes de um usuário existente via PUT.
- **Deletar Usuário**: Remove um usuário existente via DELETE.
- **Listar Posts**: Retorna todos os posts existentes via GET.
- **Exibir Post**: Retorna dados de um post existente via GET.
- **Criar Post**: Permite a criação de um novo post via POST.
- **Atualizar Post**: Atualiza detalhes de um post existente via PUT.
- **Deletar Post**: Remove um post existente via DELETE.
- **Listar Comentários**: Retorna todos os comentários existentes via GET.
- **Exibir Comentário**: Retorna dados de um post existente via GET.
- **Criar Comentário**: Permite a criação de um novo comentário via POST.
- **Atualizar Comentário**: Atualiza detalhes de um comentário existente via PUT.
- **Deletar Comentário**: Remove um comentário existente via DELETE.
