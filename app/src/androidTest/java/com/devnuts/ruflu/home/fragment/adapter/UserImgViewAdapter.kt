package com.devnuts.ruflu.home.fragment.adapter

import android.graphics.Outline
import android.os.Build
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtill
import me.relex.circleindicator.CircleIndicator3

class UserImgViewAdapter( val pager2: ViewPager2, val indicator: CircleIndicator3) : RecyclerView.Adapter<UserImgViewAdapter.ViewHoler>(){

    private lateinit var view : View
    private lateinit var imgs: List<String>
    inner class ViewHoler(itemView : View): RecyclerView.ViewHolder(itemView) {
        private lateinit var imgView : ImageView
        fun bind(img:String) {
            imgView = itemView.findViewById<ImageView>(R.id.cardimgview)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        outline!!.setRoundRect(0, 0, view!!.width, view!!.height, 20f)
                    }

                }
                imgView.outlineProvider = outlineProvider

                imgView.clipToOutline = true
            }
            //UserUtill.setImageWithGlide(view, img, imgView)
            Log.d("UserCardViewAdapter", "onCreateViewHolder ${img} ")
            UserUtill.setImageWithPiccaso(view, img, imgView)
            //UserUtill.setImageBitmap(img, imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_img_layout, parent, false)
        view.setBackgroundResource(R.drawable.user_card_style)
        initListener()

        return ViewHoler(view)
    }

    private fun initListener() {
        view.setOnTouchListener(View.OnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> setCurrentPageItem(event)
                MotionEvent.ACTION_UP -> v.performClick()
            }
            return@OnTouchListener false
        })
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        Log.d("UserCardViewAdapter", "onCreateViewHolder ${position} ")
        holder.bind(imgs[position])
    }

    override fun getItemCount(): Int = imgs.size

    fun setImgs(imgArr : List<String>) {
        imgs = imgArr
    }

    private fun setCurrentPageItem(event: MotionEvent) {
        var curNum = pager2.currentItem
        curNum = if(event.x > 550) curNum + 1 else curNum - 1

        if(curNum < 0 || curNum >= imgs.size )
            curNum = pager2.currentItem

        pager2.setCurrentItem(curNum, true)
        indicator.animatePageSelected(curNum)
    }
}