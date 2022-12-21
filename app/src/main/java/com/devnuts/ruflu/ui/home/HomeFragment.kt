package com.devnuts.ruflu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.HomeFragmentBinding
import com.devnuts.ruflu.ui.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private var savePosition: Int = 0
    private var _bind: HomeFragmentBinding? = null
    private val bind get() = _bind!!
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = HomeFragmentBinding.inflate(inflater, container, false)

        tabLayout = bind.homeTabLayout
        viewPager = bind.homeViewpager
        initView()
        return bind.root
    }

    private fun initView() {
        val homePagerAdapter = HomePagerAdapter(requireActivity())
        homePagerAdapter.addFragment(HomeSubSEFrag())
        homePagerAdapter.addFragment(HomeSubNBFrag())

        viewPager.adapter = homePagerAdapter
        viewPager.isUserInputEnabled = false

        setCompCallback()
        addListeners()
        // 뷰페이저와 tablayout 연결
        // TabLayoutMediator(tabLayout, viewPager) { tab, position -> }.attach()
    }

    private fun setCompCallback() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    private fun addListeners() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabLayout.getTabAt(savePosition)?.select()
    }

    override fun onPause() {
        super.onPause()
        savePosition = tabLayout.selectedTabPosition
    }
}
