package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.FragmentUserDetailBinding
import com.devnuts.ruflu.ui.like.viewmodel.LikeSESharedViewModel
import com.devnuts.ruflu.ui.adapter.UserImageViewAdapter
import com.devnuts.ruflu.ui.model.home.UserDtl
import me.relex.circleindicator.CircleIndicator3
import timber.log.Timber

class LikeUserDetailFragment() : Fragment() {
    private lateinit var imgAdapter: UserImageViewAdapter
    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var indicator: CircleIndicator3
    private lateinit var ratingBar: RatingBar
    private lateinit var viewPager2: ViewPager2
    private lateinit var userDtl: UserDtl

    private val parentViewModel: LikeSESharedViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
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
        imgAdapter = UserImageViewAdapter(viewPager2, indicator)
        imgAdapter.setImages(userDtl.imgs)
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
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            Timber.tag("rating").d("$rating")
            userDtl.ratingStar = rating
            parentViewModel.setUserDtl(userDtl)
        }

        parentViewModel.userDtl.observe(viewLifecycleOwner) {}
    }

    override fun onDetach() {
        super.onDetach()
        parentViewModel.detachUser()
    }
}
