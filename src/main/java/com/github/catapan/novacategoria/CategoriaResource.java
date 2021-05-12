package com.github.catapan.novacategoria;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {
    
    @POST
    @Transactional
    public Response cria(@Valid NovaCategoriaRequest request) {
        Categoria novaCategoria = new Categoria(request.getNome());
        novaCategoria.persist();

        return Response.ok(novaCategoria).build();
    }
}
