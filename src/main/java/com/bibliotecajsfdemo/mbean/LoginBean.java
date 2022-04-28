package com.bibliotecajsfdemo.mbean;

import com.bibliotecajsfdemo.model.Usuario;
import com.bibliotecajsfdemo.repository.UsuarioRepo;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UsuarioRepo usuarioRepo;

    private String email;
    private String senha;

    private Usuario usuario;

    
    public String login() {
        Optional<Usuario> usuarioOpt = usuarioRepo.obterPor(email, senha);
        if (!usuarioOpt.isPresent()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Autenticação:", "Credenciais inválidas"));
            return null;
        }
        Usuario user = usuarioOpt.get();
        this.usuario = new Usuario(user.getEmail(), user.getNome(), user.getPapel());
        return "protected/listagem?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/login?faces-redirect=true";
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
