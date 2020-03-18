package com.code4func.helloandroid.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null


    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        Log.d("MyService", "onCreate")
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }

        // If we get killed, after returning from here, restart
        return START_STICKY

        /*
            -> START_NOT_STICKY.

            Nếu hệ thống kill service khi giá trị này được trả về thì service này không được khởi động lại trừ khi có một Intent đang được chờ ở onStartCommand(). Đây là lựa chọn an toàn nhất để tránh chạy Service khi không cần thiết và khi ứng dụng có thể khởi động lại một cách đơn giản các công việc chưa hoàn thành.

            -> START_STICKY.

            Khi giá trị này được trả về trong onStartCommand, nếu service bị hệ thống kill. Nếu onStartCommand không có một intent nào chờ nó nữa thì Service sẽ được hệ thống khởi động lại với một Intent null.

            -> START_REDELEVER_INTENT

            Nếu Service bị kill thì nó sẽ được khởi động lại với một Intent là Intent cuối cùng mà Service được nhận. Điều này thích hợp với các service đang thực hiện công việc muốn tiếp tục ngay tức thì như download fie chẳng hạn. Ngoài 3 giá trị trên thì trong onStartCommand() còn có thêm 2 giá trị trả về nữa là.

            -> START_STICKY_COMPATIBILITY

            Giá trị này cũng giống như START_STICKY nhưng nó không chắc chắn, đảm bảo khởi động lại service.

            -> DEFAULT. Là một sự lựa chọn giữa START_STICKY_COMPATIBILITY hoặc START_STICKY
        */
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        Log.d("MyService", "onDestroy")
    }
}
