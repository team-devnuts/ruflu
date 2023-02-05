package com.devnuts.ruflu

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.ui.chat.fragment.ChatFragment
import com.devnuts.ruflu.ui.home.fragment.HomeFragment
import com.devnuts.ruflu.ui.like.fragment.LikeFragment
import com.devnuts.ruflu.ui.model.main.User
import com.devnuts.ruflu.ui.mypage.fragment.MyPageFragment
import com.devnuts.ruflu.worker.FusedLocationProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeFragment by lazy { HomeFragment() }
    private val likeFragment by lazy { LikeFragment() }
    private val chatFragment by lazy { ChatFragment() }
    private val myPageFragment by lazy { MyPageFragment() }
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationProvider: FusedLocationProvider

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var locationListener: LocationListener
    private lateinit var viewPager2: ViewPager2
    private var permissionCheck = PackageManager.PERMISSION_DENIED

    interface LocationListener {
        fun onLocationUpdated(location: Location)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            permissionCheck =
                applicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_ACCESS_FIND_LOCATION
            )

        // GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        initLowerTab()

        //RufluApp.sharedPreference.putSettingString("user_id", "1");

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("flow", "토큰 갱신 한다.")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("flow", "===> 토큰 존재한다. $token")
            sendRegistrationToServer(token)
        })

        fusedLocationProvider = FusedLocationProvider(applicationContext, locationListener)
        fusedLocationProvider.requestLastLocation()
        mainViewModel.user.observe(this, Observer<User> {
            // 유저 정보 변환시
            Timber.tag("유저 정보 변환")
                .i(" : ${it.latitude} , ${it.longitude}")
            RufluApp.sharedPreference.putSettingDouble("latitude", it.latitude)
            RufluApp.sharedPreference.putSettingDouble("longitude", it.longitude)
        })
    }

    private fun initLowerTab() {
        bottomNavigationView = findViewById(R.id.lower_tab)
        viewPager2 = initViewPager()
        initListener()
    }

    private fun initListener() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> viewPager2.currentItem = 0
                R.id.ruflu_menu -> viewPager2.currentItem = 1
                R.id.chat_menu -> viewPager2.currentItem = 2
                R.id.mypage_menu -> viewPager2.currentItem = 3
            }

            true
        }

        locationListener = (object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude

                mainViewModel.locationUpdate(latitude, longitude)
            }
        })
    }

    private fun initViewPager(): ViewPager2 {
        val viewPager = findViewById<ViewPager2>(R.id.main_viewpager)
        viewPager.adapter = initViewPagerAdapter()
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        return viewPager
    }

    private fun initViewPagerAdapter(): MainAdapter {
        val mainAdapter = MainAdapter(this@MainActivity)
        mainAdapter.addFragment(homeFragment)
        mainAdapter.addFragment(likeFragment)
        mainAdapter.addFragment(chatFragment)
        mainAdapter.addFragment(myPageFragment)
        return mainAdapter
    }

    private val sendRegistrationToServer = fun(token: String) {
        mainViewModel.executeFcmServiceToken(token)
    }

    companion object {
        private const val REQUEST_ACCESS_FIND_LOCATION = 1000
    }
}
