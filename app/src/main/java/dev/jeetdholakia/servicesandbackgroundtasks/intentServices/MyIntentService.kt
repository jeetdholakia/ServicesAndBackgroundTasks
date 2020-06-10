package dev.jeetdholakia.servicesandbackgroundtasks.intentServices

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import dev.jeetdholakia.servicesandbackgroundtasks.Constants
import dev.jeetdholakia.servicesandbackgroundtasks.R

class MyIntentService: IntentService("MyIntentService") {

    companion object {
        private const val TAG = "MyIntentService"
    }

    private lateinit var wakeLock: PowerManager.WakeLock

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")

        // Acquire a wakelock to keep the cpu running when the screen is OFF
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "BackgroundApp:Wakelock")
        wakeLock.acquire(10*60*1000L /*10 minutes*/)
        Log.d(TAG, "onCreate: Wakelock acquired")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(this, Constants.channelID)
                .setContentTitle(Constants.foregroundIntentServiceNotificationTitle)
                .setContentText("Running...")
                .setSmallIcon(R.drawable.ic_android_24dp)
                .build()

            startForeground(1, notification)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        // Executes on a single background thread in sequential manner
        Log.d(TAG, "onHandleIntent")

        val input = intent?.getStringExtra(Constants.inputExtra)

        for(i in 0..10) {
            Log.d(TAG, "$input - $i")
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        
        wakeLock.release()
        Log.d(TAG, "onDestroy: Wakelock released")
        super.onDestroy()
    }

}