package com.devnuts.ruflu.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate call")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart call")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart call")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume call")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause call")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop call")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy call")
    }
}