package com.code4func.helloandroid.service

import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.code4func.helloandroid.MainActivity

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ForegroundService", "Start")

        startForeground(101, updateNotification())

        return START_STICKY
    }

    private fun updateNotification(): Notification? {
        val context: Context = applicationContext
        val action = PendingIntent.getActivity(
            context,
            0, Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "123456"
            val channel = NotificationChannel(
                channelId, "ForegroundServiceDemo",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Alex channel description"
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(context)
        }
        return builder.setContentIntent(action)
            .setContentTitle("ForegroundService")
            .setTicker("ForegroundService")
            .setContentText("ForegroundService Running")
            .setSmallIcon(R.drawable.ic_lock_idle_alarm)
            .setContentIntent(action)
            .setOngoing(true)
            .build()
    }

}
