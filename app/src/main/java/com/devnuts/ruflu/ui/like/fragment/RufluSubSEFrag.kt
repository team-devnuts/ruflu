package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.RufluSubSeFragmentBinding
import com.devnuts.ruflu.ui.adapter.RufluLikeAdapter
import com.devnuts.ruflu.ui.like.viewmodel.RufluSubSEViewModel

class RufluSubSEFrag : Fragment() {

    private lateinit var viewModel: RufluSubSEViewModel
    private lateinit var bind: RufluSubSeFragmentBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: RufluLikeAdapter
    private var distance: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = RufluSubSeFragmentBinding.inflate(inflater, container, false)
        viewPager2 = bind.rufluSeViewpage2
        adapter = RufluLikeAdapter(requireActivity())
        adapter.addFragment(RufluLikeLv1Fragment())
        adapter.addFragment(RufluLikeLv2Fragment())

        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("viewPager2", "onPageSelected($position)")
            }
        })

        viewPager2.getChildAt(0).setOnTouchListener { v, event ->
            Log.d("setOnTouchListner", "" + event.action)
            if (MotionEvent.ACTION_DOWN == event.action) {
                distance = event.x
            } else if (MotionEvent.ACTION_UP == event.action) {
                distance = distance - event!!.x

                Log.d("GestureDetector", "distance $distance")

                var idx = viewPager2.currentItem
                Log.d("GestureDetector", "viewPager2.size = ${viewPager2.size}")
                if (distance > 0) {

                    if (idx == (adapter.itemCount - 1)) {
                        idx = 0
                    } else {
                        idx++
                    }
                } else {
                    if (idx == 0) {
                        idx = adapter.itemCount - 1
                    } else {
                        idx--
                    }
                }
                Log.d("GestureDetector", "idx = $idx")
                viewPager2.currentItem = idx
            }
            false
        }

        // viewPager2.isUserInputEnabled = false
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RufluSubSEViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("RufluSubSEFrag", "onAttach()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("RufluSubSEFrag", "onDetach()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("RufluSubSEFrag", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RufluSubSEFrag", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("RufluSubSEFrag", "onPause()")
    }
}
