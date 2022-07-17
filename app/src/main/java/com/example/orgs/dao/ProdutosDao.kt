package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de frutas",
                descricao = "maça, banana e manga",
                valor = BigDecimal("19.99")
            )
        )

    }

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

}