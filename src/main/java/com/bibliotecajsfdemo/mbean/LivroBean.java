package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.LivroRepo;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class LivroBean implements Serializable {

    @Inject
    private LivroRepo livroRepo;

    private Livro livro = new Livro();

    public String salvar() {
        boolean isLivroNovo = livro.getId() == null;
        if (isLivroNovo) {
            boolean jaCadastrado = livroRepo.existe(livro.getIsbn());
            if (jaCadastrado) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Livro já cadastrado com ISBN informado"));
                return null;
            }
            livroRepo.adicionar(livro);
        } else {
            livroRepo.alterar(livro);
        }
        livro = new Livro();
        return "listagem?faces-redirect=true";
    }


    public List<String> getCategorias() {
        return Arrays.asList("Romance", "Ficção", "Educacional", "Auto-Ajuda");
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
