package com.devnuts.ruflu.login.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.devnuts.ruflu.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initNavController()


        val back : ImageView = findViewById(R.id.iv_back)
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav_fragment_register) as NavHostFragment
        navController = navHostFragment.navController
    }
}