package com.code4func.helloandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        showMessage("onCreate")

        findViewById<Button>(R.id.btnGoToSecondActivity).setOnClickListener {
            // go to next activity
            val intent = Intent(this, SecondActivity::class.java)
            intent.apply {
                putExtra("MSG", "OK 123")
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnGoToSecondActivityAndReturn).setOnClickListener {
            // go to next activity
            val intent = Intent(this, SecondActivity::class.java)
            intent.apply {
                putExtra("MSG", "OK 123")
            }
            startActivityForResult(intent, RequestCode.myRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCode.myRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                /*val msgBack = data?.getStringExtra("MSG_BACK")
                if (msgBack != null) {
                    Toast.makeText(this, msgBack, Toast.LENGTH_SHORT).show()
                }*/

                data?.getStringExtra("MSG_BACK").let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    object RequestCode {
        const val myRequestCode = 123
    }

    fun Activity.showMessage(msg: String) {
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        showMessage("onRestart")
    }

    override fun onStart() {
        super.onStart()
        showMessage("onStart")
    }

    override fun onResume() {
        super.onResume()
        showMessage("onResume")
    }

    override fun onPause() {
        super.onPause()
        showMessage("onPause")
    }

    override fun onStop() {
        super.onStop()
        showMessage("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showMessage("onDestroy")
    }
}
