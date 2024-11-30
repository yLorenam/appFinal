package com.example.appfinal

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import kotlin.random.Random

class SorteioActivity : ComponentActivity() {
    private var usuarioList: List<Item> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorteio)

        val tvSorteado: TextView = findViewById(R.id.tvSorteado)
        val btnSorteio: Button = findViewById(R.id.btnSorteio)

        // Consome a API com Retrofit
        RetrofitInstance.api.getList().enqueue(object : retrofit2.Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: retrofit2.Response<List<Item>>) {
                if (response.isSuccessful && response.body() != null) {
                    usuarioList = response.body()!!
                } else {
                    Toast.makeText(this@SorteioActivity, "Erro ao carregar lista.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(this@SorteioActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        btnSorteio.setOnClickListener {
            if (usuarioList.isNotEmpty()) {
                val sorteado = usuarioList[Random.nextInt(usuarioList.size)]
                tvSorteado.text = "Nome sorteado: ${sorteado.name}, Idade: ${sorteado.idade}"
            } else {
                Toast.makeText(this, "Carregue a lista primeiro!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
