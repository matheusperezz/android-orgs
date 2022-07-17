package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tentaCarregarImagem(url : String? = null){

    load(url) {
        fallback(com.example.orgs.R.drawable.imagem_padrao)
        error(com.example.orgs.R.drawable.erro)
        fallback(com.example.orgs.R.drawable.placeholder)
    }
}