package com.bibliotecajsfdemo.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1L, message = "{campo.invalido.tamanho}")
    @Max(value = 9223372036854775807L, message = "{campo.invalido.tamanho}")
    private Long id;

    @NotNull(message = "{campo.obrigatorio}")
    @FutureOrPresent(message = "{campo.invalido.data}")
    private LocalDate dataDevolucao;

    @NotNull(message = "{campo.obrigatorio}")
    @PastOrPresent(message = "{campo.invalido.data}")
    private LocalDate dataEmprestimo;

    @NotNull(message = "{campo.obrigatorio}")
    private Livro livro;

    @NotNull(message = "{campo.obrigatorio}")
    private Leitor leitor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id) && Objects.equals(dataDevolucao, that.dataDevolucao) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(livro, that.livro) && Objects.equals(leitor, that.leitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataDevolucao, dataEmprestimo, livro, leitor);
    }

    public Emprestimo() {
        livro = new Livro();
        leitor = new Leitor();
    }

    public Emprestimo(Long id, LocalDate dataDevolucao, Livro livro, Leitor leitor) {
        this.id = id;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.leitor = leitor;
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", dataDevolucao=" + dataDevolucao +
                ", livro=" + livro +
                ", leitor=" + leitor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

}
