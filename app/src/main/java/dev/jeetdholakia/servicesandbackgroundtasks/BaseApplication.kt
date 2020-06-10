package dev.jeetdholakia.servicesandbackgroundtasks

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.channelID
import dev.jeetdholakia.servicesandbackgroundtasks.Constants.Companion.notificationChannelName

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel: NotificationChannel = NotificationChannel(
                channelID,
                notificationChannelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(serviceChannel)
        }
    }
}