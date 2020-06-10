package dev.jeetdholakia.servicesandbackgroundtasks.foregroundservices

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import dev.jeetdholakia.servicesandbackgroundtasks.Constants
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.channelID
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.notificationTitle
import dev.jeetdholakia.servicesandbackgroundtasks.R

class MyForegroundService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra(Constants.inputExtra)

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle(notificationTitle)
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_android_24dp)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}