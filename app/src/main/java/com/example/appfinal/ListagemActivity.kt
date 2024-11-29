package com.example.appfinal

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class ListagemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val url = "https://674952ec8680202966307f5f.mockapi.io/user"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val itemList = mutableListOf<Usuario>()
                parseResponse(response, itemList)
                val adapter = UsuarioAdapter(itemList)
                recyclerView.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Erro ao carregar lista: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray, itemList: MutableList<Usuario>) {
        for (i in 0 until response.length()) {
            val usuario = response.getJSONObject(i)
            val nome = usuario.getString("nome")
            val idade = usuario.getInt("idade")
            itemList.add(Usuario(nome, idade))
        }
    }
}
