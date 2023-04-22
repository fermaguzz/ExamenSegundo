package com.example.examensegundo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.iest.integracion_servicios.models.Videojuego

class OpcionesAdapter(opciones: ArrayList<Opciones>, context: Context) : RecyclerView.Adapter<OpcionesAdapter.ContenedorDeVista>() {

    var inner_opciones: ArrayList<Opciones> = opciones
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        //aqui haremos el inflate del layout
        val tvNombre: TextView
        val ivImagen: ImageView


        init {
            //Define click listener for the ViewHolder's View.
            tvNombre= view.findViewById(R.id.tvNombre)
            ivImagen = view.findViewById(R.id.ivImagen)

        }

        override fun onClick(p0: View?) {
            if (adapterPosition >= 0) {
                //Necesario usar contexto
                val miSharedPreferences = inner_context.getSharedPreferences("PERSISTENCIA",
                    AppCompatActivity.MODE_PRIVATE
                )

                val opciones: Opciones = inner_opciones.get(adapterPosition)
                if (opcion.nombre == "Gato") {
                    Toast.makeText(inner_context, "Es gato", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(inner_context, "Compra derealizada con exito!", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        return ContenedorDeVista(view)
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val opciones: Opciones = inner_opciones.get(position)
        holder.tvNombre.text = opciones.nombre
        holder.ivImagen.setImageResource(opciones.imagen)
    }

    override fun getItemCount(): Int {
        return inner_opciones.size
    }
}
