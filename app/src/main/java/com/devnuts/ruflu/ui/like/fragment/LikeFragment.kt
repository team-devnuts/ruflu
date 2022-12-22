package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.adapter.LikePagerAdapter
import com.devnuts.ruflu.ui.like.viewmodel.LikeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LikeFragment : Fragment() {

    private val tabTextList = arrayListOf(TAB_ONE, TAB_TWO)
    private var savePosition: Int = 0
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var viewModel: LikeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LikeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_like, container, false)
        viewPager = view.findViewById(R.id.ruflu_viewpager)
        tabLayout = view.findViewById(R.id.ruflu_tabLayout)

        val likePagerAdapter = LikePagerAdapter(requireActivity())
        likePagerAdapter.addFragment(LikeSubSEFragment())
        likePagerAdapter.addFragment(LikeSubNBFragment())

        viewPager.adapter = likePagerAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout.getTabAt(savePosition)?.select()
    }

    override fun onPause() {
        super.onPause()
        savePosition = tabLayout.selectedTabPosition
    }

    companion object {
        fun newInstance() = LikeFragment()
        private const val TAB_ONE = "Ruflu"
        private const val TAB_TWO = "Nearby"
    }
}
