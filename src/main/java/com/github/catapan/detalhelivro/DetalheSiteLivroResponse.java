package com.github.catapan.detalhelivro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.github.catapan.novolivro.Livro;

public class DetalheSiteLivroResponse {

    public String titulo;
    public DetalheSiteAutorResponse autor;
    public String isbn;
    public int numeroPaginas;
    public BigDecimal preco;
    public String resumo;
    public String sumario;
    public String dataPublicacao;

    public DetalheSiteLivroResponse(Livro livro) {
        titulo = livro.getTitulo();
        autor = new DetalheSiteAutorResponse(livro.getAutor());
        isbn = livro.getIsbn();
        numeroPaginas = livro.getNumeroPaginas();
        preco = livro.getPreco();
        resumo = livro.getResumo();
        sumario = livro.getSumario();
        dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public DetalheSiteAutorResponse getAutor() {
        return this.autor;
    }

    public void setAutor(DetalheSiteAutorResponse autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getResumo() {
        return this.resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return this.sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getDataPublicacao() {
        return this.dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

}
