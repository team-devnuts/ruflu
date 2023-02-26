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
import com.devnuts.ruflu.databinding.ActivityMainBinding
import com.devnuts.ruflu.ui.chat.fragment.ChatFragment
import com.devnuts.ruflu.ui.home.fragment.HomeFragment
import com.devnuts.ruflu.ui.model.main.User
import com.devnuts.ruflu.ui.mypage.fragment.MyPageFragment
import com.devnuts.ruflu.ui.some.SomeFragment
import com.devnuts.ruflu.worker.location.FusedLocationProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeFragment by lazy { HomeFragment() }
    private val likeFragment by lazy { SomeFragment() }
    private val chatFragment by lazy { ChatFragment() }
    private val myPageFragment by lazy { MyPageFragment() }
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProvider: FusedLocationProvider
    private lateinit var locationListener: LocationListener
    private var permissionCheck = PackageManager.PERMISSION_DENIED

    interface LocationListener {
        fun onLocationUpdated(location: Location)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPermissions()
        initView()
        initListener()
        searchFCMToken()
        getDeviceLocation()
        initObserves()
    }

    private fun initObserves() {
        mainViewModel.user.observe(this, Observer<User> {
            // 유저 정보 변환시
            Timber.tag("유저 정보 변환")
                .i(" : ${it.latitude} , ${it.longitude}")

            RufluApp.sharedPreference.putSettingDouble("latitude", it.latitude)
            RufluApp.sharedPreference.putSettingDouble("longitude", it.longitude)
        })
    }

    private fun getDeviceLocation() {
        fusedLocationProvider = FusedLocationProvider(applicationContext, locationListener)
        fusedLocationProvider.requestLastLocation()
    }

    private fun getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            permissionCheck =
                applicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_ACCESS_FIND_LOCATION
            )
    }

    /**
     * 현재 토큰을 검색 하는 함수
     */
    private fun searchFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("flow"," 토큰을 갱신한다.")
                Log.d("flow", "====> ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("flow","====> t삽질? ${token}")
            sendRegistrationToServer(token)
        })
    }

    private fun initView() {
        with(binding.vp2Main) {
            adapter = initViewPagerAdapter()
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }
    }

    private fun initListener() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> binding.vp2Main.currentItem = 0
                R.id.ruflu_menu -> binding.vp2Main.currentItem = 1
                R.id.chat_menu -> binding.vp2Main.currentItem = 2
                R.id.mypage_menu -> binding.vp2Main.currentItem = 3
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

    private fun initViewPagerAdapter(): MainAdapter {
        val mainAdapter = MainAdapter(this@MainActivity)
        mainAdapter.addFragment(homeFragment)
        mainAdapter.addFragment(likeFragment)
        mainAdapter.addFragment(chatFragment)
        mainAdapter.addFragment(myPageFragment)
        return mainAdapter
    }

    private val sendRegistrationToServer = fun(token: String) {
        Log.d("flow", "remote 콜!")
        mainViewModel.executeFcmServiceToken(token)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_ACCESS_FIND_LOCATION = 1000
    }
}
