package com.example.examensegundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.prefs.Preferences

class Recycler : AppCompatActivity() {

    private var cantidadColumnas = 2
    private var orientacion = GridLayoutManager.VERTICAL
    private lateinit var administradorDeLayouts: GridLayoutManager
    private lateinit var recycler: RecyclerView
    private var tvDueno: String = ""
    private var tvGato: String = ""
    private var tvEdad: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        val opciones = FakerOpciones().getOpciones()
        recycler = findViewById<RecyclerView>(R.id.recyclerMenu)

        administradorDeLayouts = GridLayoutManager(this, cantidadColumnas, orientacion, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = OpcionesAdapter(opciones, this)

        cantidadColumnas = 2
        actualizarLayout()


        val Save: View = findViewById(R.id.Save)
        Save.setOnClickListener { view ->
            val i = Intent(this, Preferences::class.java)
            if (!isChecked) {
                i.putExtra("dueño", tvDueno)
                i.putExtra("edad", tvGato)
                i.putExtra("altura", tvEdad)
            }
        startActivity(i)

        }
    }



    private fun actualizarLayout() {
        administradorDeLayouts = GridLayoutManager(this, cantidadColumnas, orientacion, false)
        recycler.layoutManager = administradorDeLayouts
    }

    }
