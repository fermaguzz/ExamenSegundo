package com.example.examensegundo

class FakerOpciones {

    fun getOpciones() : ArrayList<Opciones>{
        val opciones : ArrayList<Opciones> = arrayListOf<Opciones>()

        val opcion = Opciones(1, "Gatos", R.drawable.cat)
        opciones.add(opcion)//1
        opciones.add(Opciones(2, "Perfil", R.drawable.profile))
        opciones.add(Opciones(3, "Configurar", R.drawable.config))
        opciones.add(Opciones(4, "Cerrar", R.drawable.close))

        return opciones
    }
}