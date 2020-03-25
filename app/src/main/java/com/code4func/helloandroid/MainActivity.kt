package com.code4func.helloandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var txtResult: TextView
    lateinit var editNumber1: EditText
    lateinit var editNumber2: EditText
    lateinit var btnCaculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_demo)

        editNumber1 = findViewById(R.id.editNumber1)
        editNumber2 = findViewById(R.id.editNumber2)

        txtResult = findViewById(R.id.txtResult)
        btnCaculate = findViewById(R.id.btnCaculate)

        btnCaculate.setOnClickListener {
            try {
                val strNumber1 = editNumber1.editableText.toString()
                val strNumber2 = editNumber2.editableText.toString()

                val number1 = strNumber1.toInt()
                val number2 = strNumber2.toInt()

                val result = number1 + number2
                txtResult.text = "$result"
            } catch (e: Exception) {
                Log.e("CODE4FUNC", e.toString())
            }
        }


    }
}
