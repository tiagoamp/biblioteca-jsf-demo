package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.LeitorRepo;
import com.bibliotecajsfdemo.repository.LivroRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ListagemBean implements Serializable {

    private Logger logger = LogManager.getLogger(ListagemBean.class);

    @Inject
    private LivroRepo livroRepo;
    @Inject
    private LivroBean livroBean;
    @Inject
    private LeitorRepo leitorRepo;
    @Inject
    private LeitorBean leitorBean;

    private List<Livro> livros = new ArrayList<>();
    private List<Leitor> leitores = new ArrayList<>();

    public void onload() {   // vem do  f:event type="preRenderView"
        List<Livro> livrosBD = livroRepo.obterLivros();
        livros = livrosBD;
        List<Leitor> leitoresBD = leitorRepo.obterLeitores();
        leitores = leitoresBD;
        logger.info("Carregado " + livros.size() + " livros.");
    }

    public String editarLivro(Livro livroSelecionado) {
        livroBean.setLivro(livroSelecionado);
        return "cadastrolivro";
    }

    public String removerLivro(Livro livro) {
        livroRepo.remover(livro.getId());
        this.livros = livroRepo.obterLivros();
        return null;
    }

    public String editarLeitor(Leitor leitorSelecionado) {
        leitorBean.setLeitor(leitorSelecionado);
        return "cadastroleitor?faces-redirect=true";
    }

    public String removerLeitor(Leitor leitor) {
        leitorRepo.remover(leitor.getCpf());
        this.leitores = leitorRepo.obterLeitores();
        return null;
    }


    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Leitor> getLeitores() {
        return leitores;
    }
}
