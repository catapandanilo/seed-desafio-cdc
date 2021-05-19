package com.github.catapan.detalhelivro;

import com.github.catapan.novoautor.Autor;

public class DetalheSiteAutorResponse {

    public String nome;
    public String descricao;

    public DetalheSiteAutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
