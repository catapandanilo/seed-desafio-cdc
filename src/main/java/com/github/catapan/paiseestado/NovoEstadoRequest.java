package com.github.catapan.paiseestado;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;

import com.github.catapan.validator.DTO;
import com.github.catapan.validator.ValidDTO;

import io.smallrye.common.constraint.NotNull;

@ValidDTO
public class NovoEstadoRequest implements DTO {

    @NotBlank
    public String nome;

    @NotNull
    public Long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Estado.find("nome", nome).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Nome já cadastrado! " + this.nome)
                    .addPropertyNode("nome").addConstraintViolation();
            return false;
        }
        if (Pais.find("id", idPais).count() == 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("País não encontrado! " + this.idPais)
                    .addPropertyNode("idPais").addConstraintViolation();
            return false;
        }
        return true;
    }

    public Estado toModel() {
        return new Estado(nome, Pais.findById(idPais));
    }

}
