package dev.jeetdholakia.servicesandbackgroundtasks.jobIntentServices

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.inputExtra

class MyJobIntentService: JobIntentService() {

    companion object {
        const val TAG = "MyJobIntentService"

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, 123, intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork")

        val input = intent.getStringExtra(inputExtra)

        for(i in 0..10) {
            Log.d(TAG, "$input - $i")
            if(isStopped) return
            SystemClock.sleep(1000)
        }
    }

    override fun onStopCurrentWork(): Boolean {
        return super.onStopCurrentWork()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}