package com.code4func.helloandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val msg = intent?.getStringExtra("MSG")
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        val intent = Intent()
        intent.putExtra("MSG_BACK", "$msg back back")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
