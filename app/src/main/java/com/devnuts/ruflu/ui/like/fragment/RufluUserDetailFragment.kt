package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.UserDetailFragmentBinding
import com.devnuts.ruflu.ui.like.viewmodel.SESharedViewModel
import com.devnuts.ruflu.ui.adapter.UserImgViewAdapter
import com.devnuts.ruflu.ui.model.home.UserDtl
import me.relex.circleindicator.CircleIndicator3

class RufluUserDetailFragment() : Fragment() {

    private lateinit var imgAdapter: UserImgViewAdapter
    private lateinit var binding: UserDetailFragmentBinding
    private lateinit var indicator: CircleIndicator3
    private lateinit var ratingBar: RatingBar
    private lateinit var viewPager2: ViewPager2
    private lateinit var userDtl: UserDtl

    private val parentViewModel: SESharedViewModel by viewModels(
            ownerProducer = { requireParentFragment() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        binding.homeNbViewpage2
        ratingBar = binding.homeNbRatingBar
        indicator = binding.homeNbIndicator
        viewPager2 = binding.homeNbViewpage2

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tempUser = parentViewModel.userDtl.value
        if (tempUser != null) {
            userDtl = tempUser
            initView()
        }
    }

    private fun initView() {
        imgAdapter = UserImgViewAdapter(viewPager2, indicator)
        imgAdapter.setImgs(userDtl.imgs)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.offscreenPageLimit = 4
        viewPager2.adapter = imgAdapter
        indicator.createIndicators(imgAdapter.itemCount, 0)
        indicator.setViewPager(viewPager2)
        ratingBar.numStars = userDtl.ratingStar.toInt()

        setCompCallback()
        addListeners()
    }

    private fun setCompCallback() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    private fun addListeners() {
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.d("rating", "" + rating)
            userDtl.ratingStar = rating
            parentViewModel.setUserDtl(userDtl)
        }

        parentViewModel.userDtl.observe(viewLifecycleOwner, {
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        parentViewModel.detachUser()
    }
}
