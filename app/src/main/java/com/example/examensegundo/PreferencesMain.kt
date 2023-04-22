package com.example.examensegundo

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class PreferencesMain  : AppCompatActivity() {

=    private lateinit var dueno: EditText
    private lateinit var gato: EditText
    private lateinit var ead: EditText
    private val DUENO_KEY = "dueño"
    private val EDAD_KEY = "edad"
    private val GATO_KEY = "gato"
    private var dueno: String = ""
    private var gato: String = ""
    private var edad: Int = 0
    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preferences)
        Log.d("PREFERENCIAS", "onCreate")

        var actionBar: ActionBar?
        actionBar = supportActionBar
        var colorDrawable: ColorDrawable
        colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        inicializarVistas()

        Log.d("PREFERENCIAS", savedInstanceState?.getString(DUENO_KEY).toString())
        //nombre = savedInstanceState?.getString(NOMBRE_KEY).toString()

        val swPreferencias = findViewById<Switch>(R.id.swPreferencias)
        swPreferencias.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            isChecked = !isChecked
            Log.d("Datos", "Tus preferencias se guardan? $isChecked")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(DUENO_KEY, dueno )
        outState?.run {
            putString(DUENO_KEY, dueno)
            putInt(EDAD_KEY, edad)
            putString(GATO_KEY, gato)

        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(tvDueno)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            dueno = miSharedPreferences.getString(DUENO_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            gato = miSharedPreferences.getString(GATO_KEY, "").toString()
        }

        tvDueno.text = "Bienvenido $dueno"
        tvGato.setText(nombre)
        tvEdad.setText(edad.toString())
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }

    private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvDueno.text = "El dueño se llama " + nombre
        }
    }

    private fun inicializarVistas() {
        tvDueno = findViewById(R.id.tvDueno)
        tvGato = findViewById(R.id.tvGato)
        etEdad = findViewById(R.id.etEdad)


        bnGuardar.setOnClickListener {
            try {
                dueno = tvDueno.text.toString()
                gato = tvGato.text.toString()
                edad = tvEdad.text.toString().toInt()
                cambiarTextoBienvenida(nombre)
                val i = Intent(this, Recycler::class.java)
                if (isChecked){
                    val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                    val editor = miSharedPreferences.edit()
                    editor.putString(DUENO_KEY, nombre)
                    editor.putString(GATO_KEY, gato)
                    editor.putInt(EDAD_KEY, edad)
                    
                    editor.apply()
                }else{
                    val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                    val editor = miSharedPreferences.edit()
                    editor.putBoolean(SWITCH_KEY, isChecked)
                    editor.apply()
                }
                startActivity(i)
            } catch (e: java.lang.NumberFormatException) {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
            }
        }
    }


