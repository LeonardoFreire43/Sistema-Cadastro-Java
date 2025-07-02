# Sistema de Gerenciamento de Usuários com Spring Boot e MongoDB

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![MongoDB](https://img.shields.io/badge/MongoDB-4.x-brightgreen)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)

Este é um projeto full-stack para gerenciamento de usuários, com back-end em Java (Spring Boot) e MongoDB, e front-end em HTML, Tailwind CSS e JavaScript puro.

## ✨ Funcionalidades

- **Cadastro de Usuários:** Criação de conta com nome, email, CPF, telefone e senha.
- **Login:** Autenticação segura de usuários.
- **Recuperação de Senha:** Redefinição de senha em duas etapas.
- **Dashboard de Usuários:** Listagem de todos os usuários cadastrados.
- **Perfil de Usuário:** Visualização e edição de informações pessoais, incluindo foto de perfil.
- **Upload de Imagem:** Alteração da foto de perfil.
- **Painel de Administrador:**
    - Ativação do modo admin via senha de segurança.
    - Edição e reset de senha de qualquer usuário.
    - Exclusão de usuários.
- **Segurança:**
    - Senhas criptografadas com BCrypt.
    - Proteção de endpoints com Spring Security.
    - CORS configurado para integração segura entre front-end e back-end.

## 🛠️ Tecnologias Utilizadas

### Back-end
- Java 17
- Spring Boot 3
- Spring Data MongoDB
- Spring Security
- Maven

### Front-end
- HTML5
- Tailwind CSS
- JavaScript (ES6+)

### Banco de Dados
- MongoDB
- MongoDB Compass (opcional para visualização)

## 🚀 Como Executar o Projeto

### Pré-requisitos

- JDK 17 ou superior
- Apache Maven
- MongoDB rodando na porta padrão (`27017`)

### 1. Executando o Back-end

```bash
git clone https://github.com/LeonardoFreire43/Sistema-Cadastro-Java
cd userdatabase
mvn spring-boot:run
```

O servidor estará disponível em `http://localhost:8080`.

### 2. Executando o Front-end

Abra o arquivo `index.html` (ou `dashboard.html`, etc.) na pasta `src/main/resources/static` diretamente no navegador.

## 🛡️ Credenciais de Administrador

- Para ativar o modo admin, acesse seu perfil e clique em "Ativar Modo Admin".
- Senha de segurança: `1234`

---

Feito com ❤️ por Leonardo Freire
