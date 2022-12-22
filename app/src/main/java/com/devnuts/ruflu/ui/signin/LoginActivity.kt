package com.devnuts.ruflu.ui.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.devnuts.ruflu.R

class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ruflu)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initNavController()
    }

    /*
     FragmentContainerView 를 사용하여 NavHostFragment 를 만들 때
     또는 FragmentTransaction 을 통해 NavHostFragment 를 활동에 수동으로 추가할 경우
     Navigation.findNavController(Activity, @IdrRes. int) 를 통해
     onCreate() 에서 NavController 를 검색하려고 하면 실패합니다.
     대신, NavHostFragment 에서 직접 NavController 를 검색해야 합니다.
     */
    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav_fragment_login) as NavHostFragment
        navController = navHostFragment.navController
    }
}
