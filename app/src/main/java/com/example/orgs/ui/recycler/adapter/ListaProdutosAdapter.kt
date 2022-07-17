package com.example.orgs.ui.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ListaProdutosViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ListaProdutosViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {
            val titulo = binding.tvTitulo
            titulo.text = produto.nome

            val items = binding.tvItems
            items.text = produto.descricao

            val preco = binding.tvPreco
            val valorDaMoedaEmReal = formataParaReal(produto.valor)
            preco.text = valorDaMoedaEmReal

            val visibilidade = if (produto.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

//            Configure o load() do Coil para que apresente imagens de:
//
//            fallback: quando a imagem é nula.
//            error: quando ocorre um erro ao tentar carregar a imagem.
//            placeholder: enquanto a imagem está sendo carregada.

            binding.imageView.tentaCarregarImagem(produto.imagem)



        }

        private fun formataParaReal(valor: BigDecimal): String? {
            val formatadorParaMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatadorParaMoeda.format(valor)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaProdutosViewHolder {
//        Este método cria uma referencia ao viewholder que é responsavel por cada item individual
//        para assim fazer o binding delas, ele segura a view


//        Inflaremos a View para setarmos um padrão de layout pro recyclerView
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ListaProdutosViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListaProdutosViewHolder, position: Int) {
//        Qual posição está esta view
        val produto = produtos[position]
        holder.vincula(produto)
    }

    //        Quantos items a gente quer dentro do recyclerView
    override fun getItemCount(): Int = produtos.size


    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }


}
