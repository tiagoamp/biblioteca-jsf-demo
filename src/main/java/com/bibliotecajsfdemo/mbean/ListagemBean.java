package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Emprestimo;
import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.EmprestimoRepo;
import com.bibliotecajsfdemo.repository.LeitorRepo;
import com.bibliotecajsfdemo.repository.LivroRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Inject
    private EmprestimoRepo emprestimoRepo;
    @Inject
    private EmprestimoBean emprestimoBean;

    private List<Livro> livros = new ArrayList<>();
    private List<Leitor> leitores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void onload() {   // vem do  f:event type="preRenderView"
        List<Livro> livrosBD = livroRepo.obterLivros();
        livros = livrosBD;
        List<Leitor> leitoresBD = leitorRepo.obterLeitores();
        leitores = leitoresBD;
        List<Emprestimo> emprestimosBD = emprestimoRepo.obter();
        emprestimos = emprestimosBD;
        logger.info("Carregado " + livros.size() + " livros.");
    }

    public String editarLivro(Livro livroSelecionado) {
        livroBean.setLivro(livroSelecionado);
        return "cadastrolivro";
    }

    public String removerLivro(Livro livro) {
        List<Livro> livrosEmprestados = emprestimos.stream().map(e -> e.getLivro()).collect(Collectors.toList());
        if (livrosEmprestados.contains(livro)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Livro emprestado não pode ser excluído"));
            return null;
        }
        livroRepo.remover(livro.getId());
        this.livros = livroRepo.obterLivros();
        return null;
    }

    public String editarLeitor(Leitor leitorSelecionado) {
        leitorBean.setLeitor(leitorSelecionado);
        return "cadastroleitor?faces-redirect=true";
    }

    public String removerLeitor(Leitor leitor) {
        List<Leitor> leitoresComEmprestimo = emprestimos.stream().map(e -> e.getLeitor()).collect(Collectors.toList());
        if (leitoresComEmprestimo.contains(leitor)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Leitor tem empréstimo ativo e não pode ser excluído"));
            return null;
        }
        leitorRepo.remover(leitor.getCpf());
        this.leitores = leitorRepo.obterLeitores();
        return null;
    }

    public String consultar(Emprestimo emprestimo) {
        emprestimoBean.setEmprestimo(emprestimo);
        return "cadastroemprestimo?faces-redirect=true";
    }

    public String devolver(Emprestimo emprestimo) {
        emprestimoRepo.remover(emprestimo.getId());
        this.emprestimos = emprestimoRepo.obter();
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

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
