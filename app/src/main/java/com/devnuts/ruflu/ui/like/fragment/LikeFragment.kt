package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.FragmentLikeBinding
import com.devnuts.ruflu.ui.adapter.LikePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null
    val binding get() = _binding!!

    private val tabTextList = arrayListOf(TAB_ONE, TAB_TWO)
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initView()
    }

    private fun setupAdapter(): LikePagerAdapter {
        val likePagerAdapter = LikePagerAdapter(requireActivity())
        likePagerAdapter.addFragment(SomeFragment())
        likePagerAdapter.addFragment(MatchFragment())

        return likePagerAdapter
    }

    private fun initView() {
        with(binding) {
            vp2Like.adapter = setupAdapter()
            vp2Like.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })

            TabLayoutMediator(tlLike, vp2Like) { tab, position ->
                tab.text = tabTextList[position]
            }.attach()

            tlLike.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    vp2Like.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            vp2Like.isUserInputEnabled = false
            tlLike.getTabAt(position)?.select()
        }
    }

    override fun onPause() {
        super.onPause()
        position = binding.tlLike.selectedTabPosition
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
