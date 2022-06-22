package com.bibliotecajsfdemo.controller;

import com.bibliotecajsfdemo.dto.TesteDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/teste")
public class TesteController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.ok("Olá mundo!").build();
    }

    @GET
    @Path(("teste-dto"))
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloDto() {
        TesteDTO dto = new TesteDTO(100, "mensagem", LocalDateTime.now());
        return Response.ok(dto).build();
    }
}