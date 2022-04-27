package com.bibliotecajsfdemo.model;

public class Usuario {

    private String email;
    private String nome;
    private String senha;
    private String papel; // Role


    public Usuario() { }

    public Usuario(String email, String nome, String senha, String papel) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }

    public Usuario(String email, String nome,  String papel) {
        this(email, nome, null, papel);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
