package com.devnuts.ruflu.ui.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.adapter.ChatPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ChatFragment : Fragment() {

    private val tabTextList = arrayListOf<String>("Siloe", "Nearby")
    private var savePostion: Int = 0
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var viewModel: ChatSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.chat_fragment, container, false)
        viewPager = view.findViewById(R.id.chat_viewpager)
        tabLayout = view.findViewById(R.id.chat_tabLayout)

        val chatPagerAdapter = ChatPagerAdapter(requireActivity())
        chatPagerAdapter.addFragment(ChatSubSEFrag())
        chatPagerAdapter.addFragment(ChatSubNBFrag())

        viewPager.adapter = chatPagerAdapter
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

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatSharedViewModel::class.java)



        tabLayout.getTabAt(savePostion)?.select()
    }

    override fun onPause() {
        super.onPause()

        savePostion = tabLayout.selectedTabPosition
    }
}