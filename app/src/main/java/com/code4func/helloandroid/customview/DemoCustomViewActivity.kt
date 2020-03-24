package com.code4func.helloandroid.customview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.activity_demo_custom_view.*

class DemoCustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_custom_view)

        myCustomView.setOnClickListener {
            (it as MyCustomView).changeColor(Color.YELLOW)
        }
    }
}
