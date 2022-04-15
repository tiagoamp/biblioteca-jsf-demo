package com.bibliotecajsfdemo.model;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Leitor {

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 99999999L, message = "{campo.invalido.tamanho}")
    @Max(value = 99999999999L, message = "{campo.invalido.tamanho}")
    private Long cpf;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 2, max = 100, message = "{campo.invalido.tamanho}")
    private String nome;

    @PastOrPresent(message = "{campo.invalido.data}")
    private LocalDate dataDeNascimento;

    @Email(message = "{campo.invalido.formato}")
    private String email;


    public Leitor() { }

    public Leitor(Long cpf, String nome, LocalDate dataDeNascimento, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
    }


    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
