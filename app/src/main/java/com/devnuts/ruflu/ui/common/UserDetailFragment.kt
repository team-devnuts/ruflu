package com.devnuts.ruflu.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.FragmentUserDetailBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.DetailUIModel
import com.devnuts.ruflu.ui.model.home.ImageUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment (
    private val userId: String
) : Fragment() {
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val vieModel: UserDetailViewModel by viewModels()

    private val detailAdapter: ModelRecyclerViewAdapter<DetailUIModel> by lazy {
        ModelRecyclerViewAdapter()
    }

    private val imagesAdapter: ModelRecyclerViewAdapter<ImageUIModel> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onTouch(view: View, model: Model, event: MotionEvent) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> setCurrentPageItem(event)
                    MotionEvent.ACTION_UP -> view.performClick()
                }
            }

            override fun onClick(view: View, model: Model, position: Int) {}
            override fun onSwipe(position: Int, direction: Int) {}
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        vieModel.loadUserDetail(userId)
        initObserve()
    }

    private fun initObserve() {
        vieModel.userDetailInfo.observe(viewLifecycleOwner) { userDetailInfo ->
            detailAdapter.submitList(userDetailInfo.detailInfo as List<Model>)
            imagesAdapter.submitList(userDetailInfo.images as List<Model>)
        }
    }

    private fun initView() {
        with(binding) {
            vp2Image.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            vp2Image.offscreenPageLimit = 4
            ci3Image.setViewPager(vp2Image)
            vp2Image.adapter = imagesAdapter
            ci3Image.createIndicators(imagesAdapter.itemCount, 0)

        }
        binding.rvDetail.adapter = detailAdapter
        binding.vp2Image.adapter = imagesAdapter

    }

    private fun setCurrentPageItem(event: MotionEvent) {
        var curNum = binding.vp2Image.currentItem
        curNum = if (event.x > 550) curNum + 1 else curNum - 1
        if (curNum < 0 || curNum >= imagesAdapter.itemCount)
            curNum = binding.vp2Image.currentItem

        binding.vp2Image.setCurrentItem(curNum, true)
        binding.ci3Image.animatePageSelected(curNum)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
