package com.github.catapan.novoautor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class NovoAutorRequest {

    @NotBlank
    String nome;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(max = 400)
    String descricao;

    public NovoAutorRequest(@NotBlank String nome, @NotBlank @Email String email,
            @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }
}
