package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.databinding.ActivityFormularioProdutoBinding.inflate
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImage.setOnClickListener {
            FormularioImagemDialog(this).mostra(url) { image ->
                url = image
                binding.activityFormularioProdutoImage.tentaCarregarImagem(url)
            }
        }

    }

    private fun configuraBotaoSalvar() {

        val saveButton = binding.botaoSalvar
        saveButton.setOnClickListener {
            criaProduto()
            finish()
        }
    }

    private fun criaProduto() {

        val campoNome = binding.inputTextNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.inputTextDescricao
        val desc = campoDescricao.text.toString()
        val campoValor = binding.inputTextValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        val newProduto = Produto(
            nome = nome,
            descricao = desc,
            valor = valor,
            imagem = url
        )
        dao.adiciona(newProduto)
    }

}