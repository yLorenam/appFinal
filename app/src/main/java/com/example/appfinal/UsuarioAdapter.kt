package com.example.appfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.View

data class Usuario(val nome: String, val idade: Int)

class UsuarioAdapter(private val usuarios: List<Usuario>) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.nomeTextView.text = usuario.nome
        holder.idadeTextView.text = usuario.idade.toString()
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.tvNome)
        val idadeTextView: TextView = itemView.findViewById(R.id.tvIdade)
    }
}
