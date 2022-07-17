package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(
    private val context: Context,
) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (image: String) -> Unit
    ) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {

            urlPadrao?.let {
                formularioImageViewLoading.tentaCarregarImagem(it)
                textInputUrl.setText(it)
            }

            formularioImageRefreshButton.setOnClickListener {
                val url = textInputUrl.text.toString()
                formularioImageViewLoading.tentaCarregarImagem(url)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = textInputUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()

        }


    }

}