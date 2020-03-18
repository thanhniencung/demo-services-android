package com.code4func.helloandroid

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.code4func.helloandroid.service.*
import kotlinx.android.synthetic.main.activity_demo_start_service.*

class DemoStartServiceActivity : AppCompatActivity() {
    /** Messenger for communicating with the service.  */
    private var mService: Messenger? = null

    /** Flag indicating whether we have called bind on the service.  */
    private var bound: Boolean = false

    /**
     * Class for interacting with the main interface of the service.
     */
    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            Toast.makeText(applicationContext, "onServiceConnected", Toast.LENGTH_SHORT).show()
            mService = Messenger(service)
            bound = true
        }

        //Called when a connection to the Service has been lost.
        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null
            bound = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_start_service)

        ///////////////// INTENT SERVICE ////////////////
        btnStartIntentService.setOnClickListener {
            /*Intent(this, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, "param1")
                putExtra(EXTRA_PARAM2, "param2")
            }.also {
                startService(it)
            }*/

            MyIntentService.startActionFoo(this, "", "")
        }

        ///////////////// UNBOUND SERVICE ////////////////
        btnStartUnBoundService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
            }
        }

        btnStopUnBoundService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
            }
        }

        //https://developer.android.com/guide/components/bound-services#kotlin
        ///////////////// BOUND SERVICE ////////////////
        btnStartBoundService.setOnClickListener {
            Intent(this, BoundService::class.java).also { intent ->
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
            }
        }

        btnStopBoundService.setOnClickListener {
            // Unbind from the service
            if (bound) {
                unbindService(mConnection)
                bound = false
            }
        }

        btnSendMsg.setOnClickListener {
            // send message
            val msg: Message = Message.obtain(null, MSG_SAY_HELLO, 0, 0)
            try {
                mService?.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        // Foreground service
        // phai chay tren android 8.0 de sung dung startForegroundService
        btnStartForeground.setOnClickListener {
            val intent = Intent(this, ForegroundService::class.java)
            startForegroundService(intent)
        }

    }
}
