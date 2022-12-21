package com.devnuts.ruflu.ui.loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class LoadingActivity : AppCompatActivity() {

    private val binding by lazy {
     //   ActivityLoadingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(binding.root)

        Handler().postDelayed({
          //  val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)

    }

    companion object {
        private const val DURATION : Long = 3000   // Loading 시간은 3초
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}