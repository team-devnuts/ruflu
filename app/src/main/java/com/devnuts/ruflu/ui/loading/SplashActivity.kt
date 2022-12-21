package com.devnuts.ruflu.ui.loading

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.ui.signin.LoginActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash)

        Timer().schedule(1000) {
            if (hasToen()) {
                Log.d("SPLASH", hasToen().toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("SPLASH", hasToen().toString())
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun hasToen(): Boolean {
        val userToken = RufluApp.sharedPreference.getSettingString("USER_TOKEN")
        Log.d("SPLASH", userToken.toString())
        return userToken != null
    }
}
