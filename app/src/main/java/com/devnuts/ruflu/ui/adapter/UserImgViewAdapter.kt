package com.devnuts.ruflu.ui.adapter

import android.graphics.Outline
import android.os.Build
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtil
import me.relex.circleindicator.CircleIndicator3
import timber.log.Timber

class UserImgViewAdapter(private val pager2: ViewPager2, private val indicator: CircleIndicator3) :
    RecyclerView.Adapter<UserImgViewAdapter.ViewHolder>() {

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
            // UserUtil.setImageWithGlide(view, img, imgView)
            Timber.i("onCreateViewHolder $img ")
            UserUtil.setImageWithPiccaso(view, img, imgView)
            // UserUtil.setImageBitmap(img, imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_img_layout, parent, false)
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
        Timber.d("onCreateViewHolder $position ")
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
