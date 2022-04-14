package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.LivroRepo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class LivroBean implements Serializable {

    @Inject
    private LivroRepo livroRepo;

    private List<Livro> livros = new ArrayList<>();
    private Livro livro = new Livro();

    @PostConstruct
    public void carregarLivrosJaCadastrados() {
        List<Livro> livrosBD = livroRepo.obterLivros();
        livros.addAll(livrosBD);
    }

    public String salvar() {
        boolean isLivroNovo = livro.getId() == null;
        if (isLivroNovo) {
            boolean jaCadastrado = livros.stream().anyMatch(l -> l.getIsbn().longValue() == livro.getIsbn());
            if (jaCadastrado) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Livro já cadastrado com ISBN informado"));
                return null;
            }
            livroRepo.adicionar(livro);
        } else {
            livroRepo.alterar(livro);
        }
        livros = livroRepo.obterLivros();
        livro = new Livro();
        return "listagem?faces-redirect=true";
    }

    public String editarLivro(Livro livroSelecionado) {
        this.livro = livroSelecionado;
        return "cadastrolivro";
    }

    public String removerLivro(Livro livro) {
        livroRepo.remover(livro.getId());
        this.livros = livroRepo.obterLivros();
        return null;
    }

    public List<String> getCategorias() {
        return Arrays.asList("Romance", "Ficção", "Educacional", "Auto-Ajuda");
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public Livro getLivro() {
        return livro;
    }

}
