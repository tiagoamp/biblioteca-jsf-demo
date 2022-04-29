package com.bibliotecajsfdemo.repository;

import com.bibliotecajsfdemo.model.Emprestimo;
import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.model.Livro;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Fake Repository
 */
@ApplicationScoped
public class EmprestimoRepo implements Serializable {

    @Inject
    private LivroRepo livroRepo;
    @Inject
    private LeitorRepo leitorRepo;

    private List<Emprestimo> emprestimos = new ArrayList<>();

    private long idAtual = 0L;

    private long obterProximoId() {
        return ++idAtual;
    }

    public List<Emprestimo> obter() {
        for(Emprestimo emprestimo: emprestimos) {
            Livro livroAtualizado = livroRepo.obterPor(emprestimo.getLivro().getId());
            Leitor leitorAtualizado = leitorRepo.obterPor(emprestimo.getLeitor().getCpf());
            emprestimo.setLivro(livroAtualizado);
            emprestimo.setLeitor(leitorAtualizado);
        }
        return emprestimos;
    }

    public void adicionar(Emprestimo emprestimo) {
        long id = this.obterProximoId();
        emprestimo.setId(id);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimos.add(emprestimo);
    }

    public void remover(long idEmprestimo) {
        emprestimos.removeIf(e -> e.getId().longValue() == idEmprestimo);
    }

}
