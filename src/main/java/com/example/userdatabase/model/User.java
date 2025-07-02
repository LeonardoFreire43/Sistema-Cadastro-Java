package com.example.userdatabase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String numeroTelefone;
    private String iconeBase64;
    private String role;

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNumeroTelefone() { return numeroTelefone; }
    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }
    public String getIconeBase64() { return iconeBase64; }
    public void setIconeBase64(String iconeBase64) { this.iconeBase64 = iconeBase64; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}