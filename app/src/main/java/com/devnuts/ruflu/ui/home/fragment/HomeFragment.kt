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
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var savePosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setCompCallback()
    }

    private fun initView() {
        val homePagerAdapter = HomePagerAdapter(requireActivity())
        homePagerAdapter.addFragment(CardFragment())

        with(binding) {
            vp2Home.adapter = homePagerAdapter
            vp2Home.isUserInputEnabled = false
        }

        binding.tlHome.getTabAt(savePosition)?.select()
    }

    private fun setCompCallback() {
        binding.vp2Home.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}
