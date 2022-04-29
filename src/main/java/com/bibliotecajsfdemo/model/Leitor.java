package com.bibliotecajsfdemo.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

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

    @Size(min = 11, max = 20, message = "{campo.invalido.tamanho}")
    private String telefone;


    public Leitor() { }

    public Leitor(Long cpf, String nome, LocalDate dataDeNascimento, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leitor leitor = (Leitor) o;
        return cpf.equals(leitor.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Leitor{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
