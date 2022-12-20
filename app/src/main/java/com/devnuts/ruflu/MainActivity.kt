package com.devnuts.ruflu

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.activity.viewModels
import androidx.core.app.ActivityCompat

import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.ui.chat.ChatFragment
import com.devnuts.ruflu.comm.FusedLocationProvider
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.home.fragment.HomeFragment
import com.devnuts.ruflu.mmodel.User
import com.devnuts.ruflu.mypage.fragment.MypageFragment
import com.devnuts.ruflu.ruflu.fragment.RufluFragment
import com.google.android.gms.tasks.OnCompleteListener

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    private val homeFragment by lazy { HomeFragment() }
    private val rufluFragment by lazy { RufluFragment() }
    private val chatFragment by lazy { ChatFragment() }
    private val mypageFragment by lazy { MypageFragment() }
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var fusedLocationProvider: FusedLocationProvider
    private val REQUEST_ACCESS_FIND_LOCATION = 1000
    private val TAG = "MainActivity"
    
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var locationListener: LocationListener
    private lateinit var viewPager2: ViewPager2
    private var permissionCheck = PackageManager.PERMISSION_DENIED

    interface LocationListener {
        fun onLocationUpdated(location : Location)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            permissionCheck = applicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)


        if(permissionCheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_ACCESS_FIND_LOCATION)

        //GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        initLowerTab()

        RufluApp.sharedPreference.putSettingString("user_id", "1")
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCMService 토근 갱신", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token

            val token = task.result
            Log.d("MainActiviy 토근 갱신", "token [${token}]")
            sendRegistrationToServer(token)
        })

        fusedLocationProvider = FusedLocationProvider(applicationContext,  locationListener)
        fusedLocationProvider.requestLastLocation()
        mainViewModel.user.observe(this, Observer<User>{
            //유저정보 변환시
            Log.d(TAG, "유저 정보 변환 : ${it.latitude} , ${it.longitude}")
            RufluApp.sharedPreference.putSettingString("latitude", it.latitude.toString())
            RufluApp.sharedPreference.putSettingString("longitude", it.longitude.toString())
        } )
    }


    private fun initLowerTab() {
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.lower_tab)
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
                Log.d("Test", "GPS Location changed, Latitude: $latitude" +
                        ", Longitude: $longitude")
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
        mainAdapter.addFragment(rufluFragment)
        mainAdapter.addFragment(chatFragment)
        mainAdapter.addFragment(mypageFragment)
        return mainAdapter
    }

    private val sendRegistrationToServer = fun(token:String) {
        mainViewModel.executeFcmServiceToken(token)
    }

}