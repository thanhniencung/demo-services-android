package com.code4func.helloandroid.resource

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.activity_demo_resource.*
import java.io.IOException
import java.io.InputStream


class DemoResourceActivity : AppCompatActivity() {

    //values - https://developer.android.com/guide/topics/resources/more-resources
    // https://fonts.google.com/specimen/Roboto
    // drawable - https://developer.android.com/training/multiscreen/screendensities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_resource)

        val typeface = Typeface.createFromAsset(assets, "fonts/roboto/Roboto-Italic.ttf")
        btnClick.typeface = typeface

        val assetsString = readAssetsFile()
        Toast.makeText(this, assetsString, Toast.LENGTH_SHORT).show()
    }

    private fun readAssetsFile() : String? {
        var input: InputStream? = null
        try {
            input = assets.open("config/config.json")
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()

            // byte buffer into a string
            return  String(buffer)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } finally {
            input?.close()
        }

        return null
    }
}
