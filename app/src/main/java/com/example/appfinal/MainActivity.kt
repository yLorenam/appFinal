package com.example.appfinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCadastro: Button = findViewById(R.id.btnCadastro)
        val btnListagem: Button = findViewById(R.id.btnListagem)

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btnListagem.setOnClickListener {
            val intent = Intent(this, ListagemActivity::class.java)
            startActivity(intent)
        }
    }
}
