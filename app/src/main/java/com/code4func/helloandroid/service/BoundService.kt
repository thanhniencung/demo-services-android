package com.code4func.helloandroid.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

const val MSG_SAY_HELLO = 1

class BoundService : Service() {
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private lateinit var mMessenger: Messenger

    /**
     * Handler of incoming messages from clients.
     */
    private class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO ->
                    Toast.makeText(applicationContext, "hello!", Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Bound Service Destroy", Toast.LENGTH_SHORT).show()
    }
}
