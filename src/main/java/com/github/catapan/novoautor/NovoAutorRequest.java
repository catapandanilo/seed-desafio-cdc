package com.github.catapan.novoautor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.catapan.validator.UniqueValue;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class NovoAutorRequest {

    @NotBlank
    public String nome;

    @NotBlank
    @Email
    @UniqueValue(entity = Autor.class, field = "email")
    public String email;

    @NotBlank
    @Size(max = 400)
    public String descricao;

    public NovoAutorRequest(@NotBlank String nome, @NotBlank @Email String email,
            @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}
}
