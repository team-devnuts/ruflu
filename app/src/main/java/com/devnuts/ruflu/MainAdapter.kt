package com.devnuts.ruflu

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainAdapter(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    private var fragments: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemChanged(fragments.size - 1)
    }

    fun removeFragment() {
        fragments.removeLast()
        notifyItemChanged(fragments.size - 1)
    }
}
