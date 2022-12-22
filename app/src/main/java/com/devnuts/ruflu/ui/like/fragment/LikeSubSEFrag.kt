package com.devnuts.ruflu.ui.like.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.RufluSubSeFragmentBinding
import com.devnuts.ruflu.ui.adapter.LikeAdapter
import com.devnuts.ruflu.ui.like.viewmodel.LikeSubSEViewModel
import timber.log.Timber

class LikeSubSEFrag : Fragment() {

    private lateinit var viewModel: LikeSubSEViewModel
    private lateinit var bind: RufluSubSeFragmentBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: LikeAdapter
    private var distance: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LikeSubSEViewModel::class.java)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = RufluSubSeFragmentBinding.inflate(inflater, container, false)
        viewPager2 = bind.rufluSeViewpage2
        adapter = LikeAdapter(requireActivity())
        adapter.addFragment(LikeLv1Fragment())
        adapter.addFragment(LikeLv2Fragment())

        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Timber.d("onPageSelected($position)")
            }
        })

        viewPager2.getChildAt(0).setOnTouchListener { _, event ->
            Timber.tag("setOnTouchListener").d("${event.action}")

            if (MotionEvent.ACTION_DOWN == event.action) {
                distance = event.x
            } else if (MotionEvent.ACTION_UP == event.action) {
                distance -= event!!.x
                Timber.tag("GestureDetector").d("distance $distance")

                var idx = viewPager2.currentItem
                Timber.tag("GestureDetector").d("viewPager2.size = ${viewPager2.size}")

                if (distance > 0) {
                    if (idx == (adapter.itemCount - 1)) idx = 0
                    else idx++
                } else {
                    if (idx == 0) idx = adapter.itemCount - 1
                    else idx--
                }
                Timber.tag("GestureDetector").d("idx = $idx")

                viewPager2.currentItem = idx
            }
            false
        }

        // viewPager2.isUserInputEnabled = false
        return bind.root
    }
}
