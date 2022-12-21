package com.devnuts.ruflu.comm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.retrofit.RufluApp
import okhttp3.internal.notify

class AppNotification(rufluApp: RufluApp) {
    private val importance = NotificationManager.IMPORTANCE_HIGH
    private lateinit var channel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private val notificationId = 0

    init {
        createNotificationChannel(rufluApp)
    }

    fun notifyGeneralNotification(context: Context, title: String?, content: String?, pendingIntent: PendingIntent) {
        val GROUP_KEY_WORK_NOTIFI = "com.ruflu.notification.test"
        Log.d("AppNotification", "${context.applicationInfo.className}, $title, $content, $pendingIntent")
        // var msg = NotificationCompat.MessagingStyle.Message(content, )

        var builder = NotificationCompat.Builder(context, R.string.channel_id.toString())
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(importance)
                .setContentIntent(pendingIntent)
                .setGroup(GROUP_KEY_WORK_NOTIFI)
                .setAutoCancel(true)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun createNotificationChannel(rufluApp: RufluApp) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = rufluApp.getString(R.string.channel_name)
            val descriptionText = rufluApp.getString(R.string.channel_description)
            channel = NotificationChannel(R.string.channel_id.toString(), name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            notificationManager = rufluApp.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
