package com.example.appfinal

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import kotlin.random.Random

class SorteioActivity : ComponentActivity() {
    private var usuarioList: List<Usuario> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorteio)

        val tvSorteado: TextView = findViewById(R.id.tvSorteado)
        val btnSorteio: Button = findViewById(R.id.btnSorteio)

        val url = "https://674952ec8680202966307f5f.mockapi.io/user"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                parseResponse(response)
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Erro ao carregar lista: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(jsonArrayRequest)

        btnSorteio.setOnClickListener {
            if (usuarioList.isNotEmpty()) {
                val sorteado = usuarioList[Random.nextInt(usuarioList.size)]
                tvSorteado.text = "Nome sorteado: ${sorteado.nome}, Idade: ${sorteado.idade}"
            } else {
                Toast.makeText(this, "Carregue a lista primeiro!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parseResponse(response: JSONArray) {
        val parsedList = mutableListOf<Usuario>()
        for (i in 0 until response.length()) {
            val usuario = response.getJSONObject(i)
            val nome = usuario.getString("nome")
            val idade = usuario.getInt("idade")
            parsedList.add(Usuario(nome, idade))
        }
        usuarioList = parsedList
    }
}
