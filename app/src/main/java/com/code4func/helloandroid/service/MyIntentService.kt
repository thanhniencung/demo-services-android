package com.code4func.helloandroid.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
const val ACTION_FOO = "com.code4func.helloandroid.service.action.FOO"
const val ACTION_BAZ = "com.code4func.helloandroid.service.action.BAZ"

// TODO: Rename parameters
const val EXTRA_PARAM1 = "com.code4func.helloandroid.service.extra.PARAM1"
const val EXTRA_PARAM2 = "com.code4func.helloandroid.service.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService", Thread.currentThread().name)
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
        }
    }

    private fun handleActionFoo(param1: String, param2: String) {
       Log.d("handleActionFoo", "START")
       Thread.sleep(5000L)
       Log.d("handleActionFoo", "DONE")
    }

    companion object {
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("handleActionFoo", "onDestroy")
    }
}
