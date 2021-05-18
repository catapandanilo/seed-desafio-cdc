package com.github.catapan.novoautor;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.catapan.validator.DTO;
import com.github.catapan.validator.ValidDTO;

@ValidDTO
public class NovoAutorRequest implements DTO {

	@NotBlank
	public String nome;

	@NotBlank
	@Email
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

	@Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		constraintValidatorContext.disableDefaultConstraintViolation();
		if (Autor.find("email", email).count() > 0) {
			constraintValidatorContext.buildConstraintViolationWithTemplate("Email já cadastrado!")
					.addPropertyNode("email").addConstraintViolation();
			return false;
		}
		return true;
	}
}
