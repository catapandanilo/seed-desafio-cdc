package com.github.catapan.detalhelivro;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.catapan.novolivro.Livro;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalheLivroSiteResource {

    @GET()
    @Path("/{id}")
    public Response detalhe(@PathParam("id") Long id) {

        Livro livroBuscado = Livro.findById(id);
        if (livroBuscado == null) {
            Response.status(Status.NOT_FOUND).build();
        }

        DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(livroBuscado);
        return Response.ok(detalheSiteLivroResponse).build();
    }
}
