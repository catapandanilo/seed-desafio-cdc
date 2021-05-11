package com.github.catapan.novoautor;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutoresResource {

    @POST
    @Transactional
    public Response cria(@Valid NovoAutorRequest request) {
        Autor novoAutor = request.toModel();
        novoAutor.persist();
        return Response.ok(novoAutor).build();
    }
}