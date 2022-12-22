package com.devnuts.ruflu.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.devnuts.ruflu.R
import com.devnuts.ruflu.util.RufluApp
import timber.log.Timber

class AppNotification(rufluApp: RufluApp) {
    @RequiresApi(Build.VERSION_CODES.N)
    private val importance = NotificationManager.IMPORTANCE_HIGH
    private lateinit var channel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private val notificationId = 0

    init {
        createNotificationChannel(rufluApp)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun notifyGeneralNotification(
        context: Context,
        title: String?,
        content: String?,
        pendingIntent: PendingIntent
    ) {
        Timber.tag("AppNotification")
            .d("${context.applicationInfo.className}, $title, $content, $pendingIntent")
        // var msg = NotificationCompat.MessagingStyle.Message(content, )

        val builder = NotificationCompat.Builder(context, R.string.channel_id.toString())
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
            notificationManager =
                rufluApp.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val GROUP_KEY_WORK_NOTIFI = "com.ruflu.notification.test"
    }
}
