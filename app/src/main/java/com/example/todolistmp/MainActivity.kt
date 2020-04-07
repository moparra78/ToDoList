package com.example.todolistmp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.OutputStreamWriter
import java.io.PrintStream
import java.util.*

class MainActivity : AppCompatActivity() {

    var ListaArray = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item = findViewById<EditText>(R.id.edt1)
        val agregar = findViewById(R.id.btn1) as Button

        mostrar()

        item.setOnClickListener{
            item.editableText.clear()
        }

        agregar.setOnClickListener{

            //Toast.makeText(this, "La tarea no puede ser vacia", Toast.LENGTH_SHORT).show()
            val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, ListaArray)
            lst1.adapter = adapter
            ListaArray.add(item.text.toString())
            adapter.notifyDataSetChanged()
            guardara()

        }

        lst1.setOnItemClickListener{ parent, view, position, id ->
            borrar(position)
        }

    }

    fun borrar (position: Int) {

        ListaArray.removeAt(position)
        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, ListaArray)
        lst1.adapter = adapter
        adapter.notifyDataSetChanged()
        guardarb(ListaArray)

    }

    fun guardara(){
        val archivo = OutputStreamWriter(openFileOutput(nombre, Context.MODE_APPEND))
        archivo.write(edt1.text.toString())
        archivo.flush()
        archivo.close()
        Toast.makeText(this, "Tareas guardadas en: " +filesDir , Toast.LENGTH_SHORT).show()
    }

    fun guardarb(ListaArray: MutableList<String>){
        val output = PrintStream(openFileOutput(nombre, Context.MODE_PRIVATE))
        ListaArray.forEach { output.println(it) }
        output.flush()
        output.close()
    }

    fun mostrar(){

        val leer = Scanner(openFileInput(nombre))
        while (leer.hasNextLine()){
            ListaArray.add(leer.nextLine())
        }
        leer.close()
    }

    companion object {
        const val nombre = "pruebas.txt"
    }

}
