package com.devnuts.ruflu.ui.loading

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.ui.signin.LoginActivity
import com.devnuts.ruflu.RufluApp
import java.util.*
import kotlin.concurrent.schedule
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent: Intent

        Timer().schedule(1000) {
            if (hasToken()) {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun hasToken(): Boolean {
        val userToken = RufluApp.sharedPreference.getSettingString("USER_TOKEN")
        Timber.d(userToken.toString())
        return userToken != null
    }
}
