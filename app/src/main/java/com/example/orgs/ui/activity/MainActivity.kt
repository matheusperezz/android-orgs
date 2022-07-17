package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityMainBinding
import com.example.orgs.ui.recycler.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //    Precisamos chamar o super para poder fazer as configurações da mãe Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFAB()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFAB() {
        val fabAdd = findViewById<ExtendedFloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {

            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)

        }
    }

    private fun configuraRecyclerView() {
        val recyclerListaFruta = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerListaFruta.adapter = adapter
        recyclerListaFruta.layoutManager = LinearLayoutManager(this)
    }

}