package com.bibliotecajsfdemo.repository;

import com.bibliotecajsfdemo.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class UsuarioRepo {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepo() {
        this.usuarios.add(new Usuario("john@got.com", "John Snow", "1234", "USER"));
        this.usuarios.add(new Usuario("sansa@got.com", "Sansa Stark", "1234", "ADMIN"));
    }


    public List<Usuario> obterUsuarios() {
        Collections.sort(usuarios, Comparator.comparing((Usuario::getNome)));
        return new ArrayList<>(usuarios);
    }

    public Optional<Usuario> obterPor(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email))
                .filter(u -> u.getSenha().equals(senha))
                .findFirst();
    }

}
