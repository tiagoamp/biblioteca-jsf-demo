package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Emprestimo;
import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.EmprestimoRepo;
import com.bibliotecajsfdemo.repository.LeitorRepo;
import com.bibliotecajsfdemo.repository.LivroRepo;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class EmprestimoBean implements Serializable {

    @Inject
    private LivroRepo livroRepo;
    @Inject
    private LeitorRepo leitorRepo;
    @Inject
    private EmprestimoRepo emprestimoRepo;

    private Emprestimo emprestimo = new Emprestimo();


    public String salvar() {
        Livro livroEmprestado = livroRepo.obterPor(emprestimo.getLivro().getId());
        Leitor leitorEmprestador = leitorRepo.obterPor(emprestimo.getLeitor().getCpf());
        emprestimo.setLivro(livroEmprestado);
        emprestimo.setLeitor(leitorEmprestador);
        emprestimoRepo.adicionar(emprestimo);
        emprestimo = new Emprestimo();
        return "listagem?faces-redirect=true";
    }

    public String voltar() {
        this.emprestimo = new Emprestimo();
        return "listagem?faces-redirect=true";
    }

    public List<Livro> getLivrosDisponiveis() {
        boolean isConsulta = emprestimo.getId() != null;
        if (isConsulta) {
            return Arrays.asList( emprestimo.getLivro() );
        }
        List<Livro> livrosEmprestados = emprestimoRepo.obter().stream().map(e -> e.getLivro()).collect(Collectors.toList());
        List<Livro> livrosDisponiveis = new ArrayList<>( livroRepo.obterLivros() );
        livrosDisponiveis.removeAll( livrosEmprestados );
        return livrosDisponiveis;
    }

    public List<Leitor> getLeitores() {
        return leitorRepo.obterLeitores();
    }


    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
}
