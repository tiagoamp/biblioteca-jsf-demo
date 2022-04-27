package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.repository.LivroRepo;
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
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class LivroBean implements Serializable {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Inject
    private LivroRepo livroRepo;

    private Livro livro = new Livro();

    public String salvar() {
        boolean isLivroNovo = livro.getId() == null;
        if (isLivroNovo) {
            livroRepo.adicionar(livro);
        } else {
            livroRepo.alterar(livro);
        }
        logger.debug("Livro salvo: " + livro);
        livro = new Livro();
        return "listagem?faces-redirect=true";
    }


    public void validarISBNduplicado(FacesContext facesContext, UIComponent component, Object o) throws ValidatorException {
        if (o == null)
            return;
        Long isbn = (Long) o;
        boolean jaCadastrado = livroRepo.existe(isbn);
        if (jaCadastrado) {
            logger.error("Livro jah cadastrado com ISBN: " + isbn);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", "Livro já cadastrado com ISBN informado");
            throw new ValidatorException(msg);
        }
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
