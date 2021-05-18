package com.github.catapan.novacategoria;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;

import com.github.catapan.validator.DTO;
import com.github.catapan.validator.ValidDTO;

@ValidDTO
public class NovaCategoriaRequest implements DTO {

    @NotBlank
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

    public Categoria toModel() {
		return new Categoria(this.nome);
	}

    @Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		constraintValidatorContext.disableDefaultConstraintViolation();
		if (Categoria.find("nome", nome).count() > 0) {
			constraintValidatorContext.buildConstraintViolationWithTemplate("Nome já cadastrado!")
					.addPropertyNode("nome").addConstraintViolation();
			return false;
		}
		return true;
	}

}
