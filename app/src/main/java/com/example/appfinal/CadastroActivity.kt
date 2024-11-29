package com.example.appfinal

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class CadastroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val editName: EditText = findViewById(R.id.editName)
        val editIdade: EditText = findViewById(R.id.editIdade)
        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = editName.text.toString()
            val idadeString = editIdade.text.toString()

            if (nome.isNotEmpty() && idadeString.isNotEmpty()) {
                val idade = idadeString.toIntOrNull()
                if (idade != null) {
                    val url = "https://mockapi.com/usuarios"
                    val requestQueue = Volley.newRequestQueue(this)

                    val stringRequest = object : StringRequest(
                        Request.Method.POST, url,
                        Response.Listener { response ->
                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(this, "Erro ao cadastrar: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["nome"] = nome
                            params["idade"] = idade.toString()
                            return params
                        }
                    }

                    requestQueue.add(stringRequest)
                } else {
                    Toast.makeText(this, "Idade inválida!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
