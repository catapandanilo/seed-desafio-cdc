package com.github.catapan.paiseestado;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/paises")
public class CriaPaisResource {
    
    @POST
    @Transactional
    public Response criaPais(@Valid NovoPaisRequest request) {
        Pais novoPais = request.toModel();
        novoPais.persist();

        return Response.ok(novoPais).build();
    }
}
