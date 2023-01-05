package com.devnuts.ruflu.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.databinding.ItemUserCardBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.CardImageUIModel
import com.devnuts.ruflu.ui.model.home.CardUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class CardViewHolder(
    private val binding: ItemUserCardBinding
) : ModelViewHolder<CardUIModel>(binding) {

    private val imagesAdapter: ModelRecyclerViewAdapter<CardImageUIModel> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {}

            override fun onTouch(view: View, model: Model, event: MotionEvent) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> setCurrentPageItem(event)
                    MotionEvent.ACTION_UP -> view.performClick()
                }
            }
        })
    }

    override fun bindData(model: CardUIModel) {
        binding.cardViewName.text = model.nick_nm
        binding.cardViewAge.text = "${UserUtil.getAge(model.birth)}"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindViews(model: CardUIModel, menuAdapterListener: ModelAdapterListener?) {
        binding.vp2Image.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vp2Image.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        binding.vp2Image.offscreenPageLimit = 4
        binding.ci3Image.setViewPager(binding.vp2Image)
        binding.vp2Image.adapter = imagesAdapter
        binding.ci3Image.createIndicators(imagesAdapter.itemCount, 0)

        imagesAdapter.submitList(model.images)
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