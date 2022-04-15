package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.repository.LeitorRepo;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LeitorBean implements Serializable {

    @Inject
    private LeitorRepo leitorRepo;

    private Leitor leitor = new Leitor();


    public String salvar() {
        boolean isLeitorNovo = leitor.getCpf() == null;
        if (isLeitorNovo) {
            boolean jaCadastrado = leitorRepo.existe(leitor.getCpf());
            if (jaCadastrado) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Leitor já cadastrado com CPF informado"));
                return null;
            }
            leitorRepo.adicionar(leitor);
        } else {
            leitorRepo.alterar(leitor);
        }
        leitor = new Leitor();
        return "listagem?faces-redirect=true";
    }


    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}
