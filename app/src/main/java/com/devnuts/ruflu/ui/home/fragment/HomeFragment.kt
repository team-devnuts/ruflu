package com.devnuts.ruflu.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.FragmentHomeBinding
import com.devnuts.ruflu.ui.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private var savePosition: Int = 0
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        tabLayout = binding.homeTabLayout
        viewPager = binding.homeViewpager
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout.getTabAt(savePosition)?.select()
    }

    private fun initView() {
        val homePagerAdapter = HomePagerAdapter(requireActivity())
        homePagerAdapter.addFragment(CardFragment())

        viewPager.adapter = homePagerAdapter
        viewPager.isUserInputEnabled = false

        setCompCallback()
    }

    private fun setCompCallback() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}
