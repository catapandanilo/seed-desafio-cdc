package com.github.catapan.novacategoria;

import javax.validation.constraints.NotBlank;

import com.github.catapan.validator.UniqueValue;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(entity = Categoria.class, field = "nome")
    public String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Não aceita construtuor com 1 argumento?!
    //
    // com.fasterxml.jackson.databind.exc.MismatchedInputException:
    // Cannot construct instance of
    // `com.github.catapan.novacategoria.NovaCategoriaRequest`
    // (although at least one Creator exists): cannot deserialize from Object value
    // (no delegate- or property-based Creator)
    // at [Source: (io.quarkus.vertx.http.runtime.VertxInputStream); line: 2,
    // column:5]
    // public NovaCategoriaRequest(String nome) {
    // this.nome = nome;
    // }

}
