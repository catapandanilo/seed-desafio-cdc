package com.github.catapan.paiseestado;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;

import com.github.catapan.validator.DTO;
import com.github.catapan.validator.ValidDTO;

@ValidDTO
public class NovoPaisRequest implements DTO {

    @NotBlank
    public String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Pais.find("nome", nome).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Nome já cadastrado!")
                    .addPropertyNode("nome").addConstraintViolation();
            return false;
        }
        return true;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
