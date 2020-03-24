package com.code4func.helloandroid.broadcastreceivers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code4func.helloandroid.R
import com.code4func.helloandroid.service.MyService
import kotlinx.android.synthetic.main.activity_demo_broadcast_receiver.*

class DemoBroadcastReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_broadcast_receiver)

        val intentService = Intent(this, MyService::class.java)
        startService(intentService)

        btnSendEvent.setOnClickListener {
            val intent = Intent()
            intent.action = "com.code4func.helloandroid.CODE4FUNC"
            intent.putExtra("DEMO_MSG", "123456")
            sendBroadcast(intent)
        }
    }
}
