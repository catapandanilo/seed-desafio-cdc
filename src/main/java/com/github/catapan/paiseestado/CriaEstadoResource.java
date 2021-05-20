package com.github.catapan.paiseestado;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/estados")
public class CriaEstadoResource {

    @POST
    @Transactional
    public Response criaEstado(@Valid NovoEstadoRequest request) {
        Estado estado = request.toModel();
        estado.persist();

        return Response.ok(estado).build();
    }
    
}
