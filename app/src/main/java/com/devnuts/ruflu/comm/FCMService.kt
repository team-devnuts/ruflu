package com.devnuts.ruflu.comm

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.MainRepository
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.ui.chat.fragment.ChatFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FCMService : FirebaseMessagingService() {
    private val TAG = javaClass.name
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "토큰 : $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        // 앱이 foreground 상태에 있을 때 FCM 알림을 받았다면 onMessageReceived() 콜백 메소드가 호출됨으로써 FCM 알림이 대신된다.
        Log.d("onMessageReceived 콜백", "From: ${remoteMessage.from}")

        // 메시지 유형이 데이터 메시지일 경우
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            sendNotificationMessage(remoteMessage.data)
        }

        // 메시지 유형이 알림 메시지일 경우
        // Check if message contains a notification payload.
        // Set FCM title, body to android notificatio

        var notificationInfo = mapOf<String, String>()
        remoteMessage.notification?.let {
            notificationInfo = mapOf(
                "title" to it.title.toString(),
                "body" to it.body.toString()
            )
            Log.d("FCMService", "$notificationInfo")
            sendNotificationNotification(notificationInfo)
        }

        super.onMessageReceived(remoteMessage)
    }

    private fun sendRegistrationToServer(token: String) {
        val call = MainRepository().executeFcmServiceToken(token)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (!response.isSuccessful) {
                    Log.d("Token 갱신", "fail " + response.message())
                    return
                }
                Log.d("Token 갱신", "Success")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("fail token update", t.stackTraceToString())
            }
        })
    }

    private fun sendNotificationMessage(data: Map<String, String>) {
        val pendingIntent = getPendingIntent(data["type"])
        RufluApp.appNotification.notifyGeneralNotification(
            this, data["title"],
            data["content"], pendingIntent!!
        )
    }

    private fun sendNotificationNotification(data: Map<String, String>) {
        val pendingIntent = getPendingIntent(data["type"])
        RufluApp.appNotification.notifyGeneralNotification(
            this, data["title"],
            data["body"], pendingIntent!!
        )
    }

    private fun getPendingIntent(type: String?): PendingIntent? {
        var intent: Intent? = null
        when (type) {
            "MESSAGE" -> intent = Intent(this, ChatFragment::class.java)
        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT
            )
        } else {
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }
    }
}
