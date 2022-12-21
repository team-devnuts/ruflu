package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.adapter.RufluPagerAdapter
import com.devnuts.ruflu.ui.like.viewmodel.RufluViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RufluFragment : Fragment() {

    private val tabTextList = arrayListOf<String>("Siloe", "Nearby")
    private var savePostion: Int = 0
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = RufluFragment()
    }

    private lateinit var viewModel: RufluViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.ruflu_fragment, container, false)
        viewPager = view.findViewById(R.id.ruflu_viewpager)
        tabLayout = view.findViewById(R.id.ruflu_tabLayout)

        val RufluPagerAdapter = RufluPagerAdapter(requireActivity())
        RufluPagerAdapter.addFragment(RufluSubSEFrag())
        RufluPagerAdapter.addFragment(RufluSubNBFrag())

        viewPager.adapter = RufluPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewPager.isUserInputEnabled = false
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RufluViewModel::class.java)
        tabLayout.getTabAt(savePostion)?.select()
    }

    override fun onPause() {
        super.onPause()

        savePostion = tabLayout.selectedTabPosition
    }
}
