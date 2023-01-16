package com.devnuts.ruflu.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.ItemUserCardBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class CardViewHolder(
    private val binding: ItemUserCardBinding
) : ModelViewHolder<UserUIModel>(binding) {

    override fun bindData(model: UserUIModel) {
        binding.cardViewName.text = model.nickName
        binding.cardViewAge.text = "${UserUtil.getAge(model.birth)}"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindViews(model: UserUIModel, adapterListener: ModelAdapterListener?) {
        with(binding) {
            vp2Image.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
            vp2Image.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            vp2Image.offscreenPageLimit = 4
            ci3Image.setViewPager(vp2Image)
            vp2Image.adapter = imagesAdapter
            ci3Image.createIndicators(imagesAdapter.itemCount, 0)
        }
        imagesAdapter.submitList(model.images)
    }

    private val imagesAdapter: ModelRecyclerViewAdapter<UserImageUIModel> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {}

            override fun onTouch(view: View, model: Model, event: MotionEvent) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> setCurrentPageItem(event)
                    MotionEvent.ACTION_UP -> view.performClick()
                }
            }

            override fun onSwipe(position: Int, direction: Int) {}

        })
    }

    private fun setCurrentPageItem(event: MotionEvent) {
        var curNum = binding.vp2Image.currentItem
        curNum = if (event.x > 550) curNum + 1 else curNum - 1
        if (curNum < 0 || curNum >= imagesAdapter.itemCount)
            curNum = binding.vp2Image.currentItem

        binding.vp2Image.setCurrentItem(curNum, true)
        binding.ci3Image.animatePageSelected(curNum)
    }
}