package com.pallaw.servicesandbackgroundtasks.module.foergroundservice

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.pallaw.servicesandbackgroundtasks.R
import com.pallaw.servicesandbackgroundtasks.module.landing.MainActivity

/**
 * Created by Pallaw Pathak on 06/05/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class ForegroundService : Service() {
    companion object {
        val EXTRA = "extra"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let {
            val notificationContent = it.getString(EXTRA)

            val intentToForegroundService = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intentToForegroundService, 0)

            val deepLinkPendingIntent = NavDeepLinkBuilder(this)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.foregroundServiceFragment)
                .createPendingIntent()

            val channelId =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel(
                    "foreground_service",
                    "My foreground service"
                ) else ""

            val notifiaction = NotificationCompat.Builder(this, channelId)
                .setContentTitle("Foreground service")// set title
                .setStyle(
                    NotificationCompat.BigTextStyle().bigText(notificationContent)
                )//multiline content
                .setContentText(notificationContent)// set content
                .setSmallIcon(R.drawable.ic_android)// small icon
                .setContentIntent(deepLinkPendingIntent)// set intent to be triggered when notification clicked
                .build()// build the notification

            startForeground(123, notifiaction)
        }
        return START_STICKY_COMPATIBILITY// WE DON'T CARE IF SYSTEM KILLS THIS SERVICE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}