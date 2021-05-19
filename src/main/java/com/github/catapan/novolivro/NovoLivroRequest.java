package com.github.catapan.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.github.catapan.novacategoria.Categoria;
import com.github.catapan.novoautor.Autor;
import com.github.catapan.validator.DTO;
import com.github.catapan.validator.ValidDTO;

import io.smallrye.common.constraint.NotNull;

@ValidDTO
public class NovoLivroRequest implements DTO {

    @NotBlank
    public String titulo;
    @NotBlank
    @Size(max = 500)
    public String resumo;
    @NotBlank
    public String sumario;
    @NotNull
    @Min(20)
    public BigDecimal preco;
    @Min(100)
    public int numeroPaginas;
    @NotBlank
    public String isbn;
    // @NotNull
    // @Future
    // @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    public LocalDate dataPublicacao = LocalDate.now();
    @NotNull
    public Long idAutor;
    @NotNull
    public Long idCategoria;

    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
            @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
            // @Future LocalDate dataPublicacao, 
            @NotNull Long idAutor, @NotNull Long idCategoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        // this.dataPublicacao = dataPublicacao;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Livro.find("titulo", titulo).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Título já cadastrado! " + this.titulo)
                    .addPropertyNode("titulo").addConstraintViolation();
            return false;
        }
        if (Livro.find("isbn", isbn).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("ISBN já cadastrado! " + this.isbn)
                    .addPropertyNode("isbn").addConstraintViolation();
            return false;
        }
        return true;
    }

    public Livro toModel() {
        Autor autor = Autor.findById(idAutor);
        Categoria categoria = Categoria.findById(idCategoria);
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, autor, categoria);
    }
}
