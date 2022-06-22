package com.bibliotecajsfdemo.controller;

import com.bibliotecajsfdemo.model.Livro;
import com.bibliotecajsfdemo.service.LivroService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/livros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LivroController {

    @Inject
    private LivroService livroService;

    @GET
    public Response consultar(@QueryParam("titulo") String titulo) {
        System.out.println("Titulo de consulta: " + titulo);
        List<Livro> livros = livroService.consultar();
        return Response.ok(livros).build();
    }

    @GET
    @Path("/{id}")
    public Response consultar(@PathParam("id") Long id) {
        Livro livro = livroService.consultarPorId(id);
        return Response.ok(livro).build();
    }

    @POST
    public Response inserir(Livro livro) {
        Livro inserido = livroService.inserir(livro);
        return Response.created(URI.create(inserido.getId().toString())).entity(inserido).build();
    }

}
