package com.example.todolistmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun limpiar(view: View){
        edt1.editableText.clear()
    }

    fun agregar(view: View){

        val ListaArray = ArrayList<String>()
        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, ListaArray)
        lst1.adapter = adapter

        ListaArray.add(edt1.text.toString())
        adapter.notifyDataSetChanged()

    }



}
