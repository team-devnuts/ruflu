package com.devnuts.ruflu.ui.some

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.FragmentSomeBinding
import com.devnuts.ruflu.ui.adapter.SomePagerAdapter
import com.devnuts.ruflu.ui.some.fragment.LikeFragment
import com.devnuts.ruflu.ui.some.fragment.MatchFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SomeFragment : Fragment() {
    private var _binding: FragmentSomeBinding? = null
    val binding get() = _binding!!

    private val tabTextList = arrayListOf(TAB_ONE, TAB_TWO)
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initView()
    }

    private fun setupAdapter(): SomePagerAdapter {
        val somePagerAdapter = SomePagerAdapter(requireActivity())
        somePagerAdapter.addFragment(LikeFragment())
        somePagerAdapter.addFragment(MatchFragment())

        return somePagerAdapter
    }

    private fun initView() {
        with(binding) {
            vp2Some.adapter = setupAdapter()
            vp2Some.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })

            TabLayoutMediator(tlSome, vp2Some) { tab, position ->
                tab.text = tabTextList[position]
            }.attach()

            tlSome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    vp2Some.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            vp2Some.isUserInputEnabled = false
            tlSome.getTabAt(position)?.select()
        }
    }

    override fun onPause() {
        super.onPause()
        position = binding.tlSome.selectedTabPosition
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val TAB_ONE = "LIKE"
        private const val TAB_TWO = "MATCH"
    }
}
