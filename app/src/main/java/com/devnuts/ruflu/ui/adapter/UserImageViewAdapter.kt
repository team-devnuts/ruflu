package com.devnuts.ruflu.ui.adapter

import android.graphics.Outline
import android.os.Build
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.util.UserUtil
import me.relex.circleindicator.CircleIndicator3

class UserImageViewAdapter(private val pager2: ViewPager2, private val indicator: CircleIndicator3) :
    RecyclerView.Adapter<UserImageViewAdapter.ViewHolder>() {

    private lateinit var view: View
    private lateinit var images: List<String>

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imgView: ImageView
        fun bind(img: String) {
            imgView = itemView.findViewById<ImageView>(R.id.cardimgview)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        outline!!.setRoundRect(0, 0, view!!.width, view!!.height, 20f)
                    }
                }
                imgView.outlineProvider = outlineProvider

                imgView.clipToOutline = true
            }

            UserUtil.setImageWithPiccaso(view, img, imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_img, parent, false)
        view.setBackgroundResource(R.drawable.user_card_style)
        initListener()

        return ViewHolder(view)
    }

    private fun initListener() {
        view.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> setCurrentPageItem(event)
                MotionEvent.ACTION_UP -> v.performClick()
            }
            return@OnTouchListener false
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun setImages(imgArr: List<String>) {
        images = imgArr
    }

    private fun setCurrentPageItem(event: MotionEvent) {
        var curNum = pager2.currentItem
        curNum = if (event.x > 550) curNum + 1 else curNum - 1

        if (curNum < 0 || curNum >= images.size)
            curNum = pager2.currentItem

        pager2.setCurrentItem(curNum, true)
        indicator.animatePageSelected(curNum)
    }
}
