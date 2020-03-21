package com.code4func.helloandroid.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val isEnabled = Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON , 0
        ) == 1

        if (isEnabled) {
            Toast.makeText(context, "AIRPLANE-ON", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "AIRPLANE-OFF", Toast.LENGTH_SHORT).show()
        }
    }
}
