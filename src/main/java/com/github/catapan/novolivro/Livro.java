package com.github.catapan.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.github.catapan.novacategoria.Categoria;
import com.github.catapan.novoautor.Autor;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Livro extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
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
    @NotNull
    // @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    public LocalDate dataPublicacao = LocalDate.now();
    @ManyToOne
    public @Valid Autor autor;
    @ManyToOne
    public @Valid Categoria categoria;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
            @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
            @NotNull Autor autor, @NotNull Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.autor = autor;
        this.categoria = categoria;
    }

}
