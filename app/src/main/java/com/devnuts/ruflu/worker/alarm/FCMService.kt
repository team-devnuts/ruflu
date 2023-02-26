package com.devnuts.ruflu.worker.alarm

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.domain.repository.MainRepository
import com.devnuts.ruflu.ui.chat.fragment.ChatFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class FCMService : FirebaseMessagingService() {
    @Inject lateinit var repository: MainRepository


    /**
     * 클라우드 서버에 등록 되었을 때 호출 됨.
     * 단말기 토큰이 바꼈을 시 할 작업
     * 새로운 token 이 생성될 때마다 호출되 callback
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("flow", "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    /**
     * 클라우드 서버에 메시지를 전송하면 자동으로 호출 됨.
     * 푸시 메시지 수신 시 할 잘업
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // 앱이 foreground 상태에 있을 때 FCM 알림을 받았다면 onMessageReceived() 콜백 메소드가 호출 됨으로써 FCM 알림이 대신된다.
        super.onMessageReceived(remoteMessage)
        Log.d("flow", "remoteMessage.from => ${remoteMessage.from}")

        // 메시지 유형이 '데이터 메시지' 일 경우
        if (remoteMessage.data.isNotEmpty()) {
            Timber.tag("Message data payload").d("${remoteMessage.data}")
            sendNotificationMessage(remoteMessage.data)
        }

        // 메시지 유형이 '알림 메시지' 일 경우
        remoteMessage.notification?.let {
            val notificationInfo = mapOf(
                "title" to it.title.toString(),
                "body" to it.body.toString()
            )

            sendNotification(notificationInfo)
        }

        super.onMessageReceived(remoteMessage)
    }

    /**
     * 루플루 앱 서버 호출 하여 토큰 update
     */
    private fun sendRegistrationToServer(token: String) {
        val call = repository.executeFcmServiceToken(token)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (!response.isSuccessful) {
                    Timber.tag("Token 갱신").d(response.message())
                    return
                }
                Timber.tag("Token 갱신").d("Success")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag("fail token update").e(t.stackTraceToString())
            }
        })
    }

    /**
     * 메시지 유형 : '데이터 메시지'
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendNotificationMessage(data: Map<String, String>) {
        val pendingIntent = getPendingIntent(data["type"])
        RufluApp.appNotification.notifyGeneralNotification(
            this, data["title"],
            data["content"], pendingIntent!!
        )
    }

    /**
     * 메시지 유형 : '알림 메시지'
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendNotification(data: Map<String, String>) {
        val pendingIntent = getPendingIntent(data["type"])
        RufluApp.appNotification.notifyGeneralNotification(
            this, data["title"],
            data["body"], pendingIntent!!
        )
    }

    /**
     * 앱 알림 발생 시켰던 위치로 돌아가기
     */
    private fun getPendingIntent(type: String?): PendingIntent? {
        var intent: Intent? = null

        // Component EndPoint
        when (type) {
            "MESSAGE" -> intent = Intent(this, ChatFragment::class.java)
        }

        return if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT
            )
        } else {
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }
    }
}
