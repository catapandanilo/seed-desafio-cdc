package com.github.catapan.novolivro;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.catapan.validator.ConstraintViolationResponse;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivrosResource {

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Caso livro seja registrado com sucesso")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
    public Response cria(@Valid NovoLivroRequest request) {
        Livro novoLivro = request.toModel();
        novoLivro.persist();

        return Response.status(Status.CREATED).build();
    }

}
