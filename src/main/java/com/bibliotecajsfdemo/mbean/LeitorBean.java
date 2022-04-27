package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Leitor;
import com.bibliotecajsfdemo.repository.LeitorRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LeitorBean implements Serializable {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Inject
    private LeitorRepo leitorRepo;

    private Leitor leitor = new Leitor();


    public String salvar() {
        boolean isLeitorNovo = leitor.getCpf() == null;
        if (isLeitorNovo) {
            leitorRepo.adicionar(leitor);
        } else {
            leitorRepo.alterar(leitor);
        }
        logger.debug("Leitor salvo: " + leitor);
        leitor = new Leitor();
        return "listagem?faces-redirect=true";
    }


    public void validarCPFduplicado(FacesContext facesContext, UIComponent component, Object o) throws ValidatorException {
        if (o == null)
            return;
        Long cpf = (Long) o;
        boolean jaCadastrado = leitorRepo.existe(cpf);
        if (jaCadastrado) {
            logger.error("Leitor jah cadastrado com CPF: " + cpf);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Leitor já cadastrado com CPF informado");
            throw new ValidatorException(msg);
        }
    }


    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}
