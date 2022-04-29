package com.bibliotecajsfdemo.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Livro {

    private Long id;

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 1111111111111L, message = "{campo.invalido.tamanho}")
    @Max(value = 9999999999999L, message = "{campo.invalido.tamanho}")
    private Long isbn;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 2, max = 100, message = "{campo.invalido.tamanho}")
    private String titulo;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 2, max = 100, message = "{campo.obrigatorio}")
    private String autor;

    @NotEmpty(message = "{campo.obrigatorio}")
    private String categoria;

    @PastOrPresent(message = "{campo.invalido.data}")
    private LocalDate dataPublicacao;

    @Min(value = 1, message = "{campo.invalido.tamanho}")
    @Max(value=9999, message = "{campo.invalido.tamanho}")
    private Integer nroDePaginas;


    public Livro() { }

    public Livro(Long id, Long isbn, String titulo, String autor, String categoria, LocalDate dataPublicacao, Integer nroDePaginas) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
        this.nroDePaginas = nroDePaginas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id) && Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNroDePaginas() {
        return nroDePaginas;
    }

    public void setNroDePaginas(Integer nroDePaginas) {
        this.nroDePaginas = nroDePaginas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
