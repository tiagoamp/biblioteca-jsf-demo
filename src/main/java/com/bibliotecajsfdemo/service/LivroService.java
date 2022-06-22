package com.bibliotecajsfdemo.service;

import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.LivroRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class LivroService {

    @Inject
    private LivroRepo livroRepo;

    public List<Livro> consultar() {
        return livroRepo.obterLivros();
    }

    public Livro consultarPorId(Long id) {
        return livroRepo.obterPor(id);
    }

    public Livro inserir(Livro livro) {
        livroRepo.adicionar(livro);
        long maiorId = livroRepo.obterLivros().stream().mapToLong(Livro::getId).max().getAsLong();
        return livroRepo.obterPor(maiorId);
    }
    
}
