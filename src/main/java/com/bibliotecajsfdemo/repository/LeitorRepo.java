package com.bibliotecajsfdemo.repository;

import com.bibliotecajsfdemo.model.Leitor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Representa um mock de repositório (banco de dados fake)
 * com dados persistentes
 */
@ApplicationScoped
public class LeitorRepo {

    private List<Leitor> leitores = new ArrayList<>();

    public LeitorRepo() {
        leitores.add(new Leitor(11122233344L, "John Snow", LocalDate.of(2000, Month.NOVEMBER, 3), "john@got.com", "(48) 99999-0101"));
        leitores.add(new Leitor(22233344455L, "Tyrion Lannister", LocalDate.of(1990, Month.DECEMBER, 13), "tyrion@got.com", "(48) 99999-0202"));
        leitores.add(new Leitor(33344455566L, "Sansa Stark", LocalDate.of(2002, Month.JULY, 2), "sansa@got.com", "(48) 99999-0303"));
        leitores.add(new Leitor(44455566677L, "Sandor Clegane", LocalDate.of(1980, Month.NOVEMBER, 3), "theround@got.com", "(48) 99999-0404"));
    }

    public List<Leitor> obterLeitores() {
        Collections.sort(leitores, Comparator.comparing(Leitor::getNome));
        return leitores;
    }

    public Leitor obterPor(Long cpf) {
        return leitores.stream().filter(l -> l.getCpf().longValue() == cpf.longValue()).findFirst().get();
    }

    public void adicionar(Leitor leitorNovo) {
        leitores.add(leitorNovo);
    }

    public void remover(long cpf) {
        leitores.removeIf(l -> l.getCpf() == cpf);
    }

    public void alterar(Leitor leitorAtualizado) {
        remover(leitorAtualizado.getCpf());
        leitores.add(leitorAtualizado);
        Collections.sort(leitores, Comparator.comparing(Leitor::getNome));
    }

    public boolean existe(long cpf) {
        return leitores.stream().anyMatch(l -> l.getCpf() == cpf);
    }

}
