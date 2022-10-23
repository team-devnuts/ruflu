package com.devnuts.ruflu.home.fragment.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtill
import com.devnuts.ruflu.home.CustomCardStackView
import com.devnuts.ruflu.home.fragment.HomeSubSEFrag
import com.devnuts.ruflu.home.model.UserCard
import me.relex.circleindicator.CircleIndicator3
import java.util.*
import kotlin.collections.ArrayList

class UserCardViewAdapter(val data : ArrayList<UserCard>, val fragment: HomeSubSEFrag, val cardStackView: CustomCardStackView)
    : RecyclerView.Adapter<UserCardViewAdapter.PagerViewHoler>(){

    private lateinit var view: View
    private lateinit var imgAdapter: UserImgViewAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicator: CircleIndicator3
    //private val animation = AnimationUtils.loadAnimation(fragment.context,R.anim.user_card_drawer_action)
    private var scrollViewY = 0f
    private var isFlagScroll = false

    inner class PagerViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageName : TextView = itemView.findViewById(R.id.card_view_name)
        private val pageAge : TextView = itemView.findViewById(R.id.card_view_age)

        fun bind(userCard: UserCard) {
            pageName.text = "${userCard.nick_nm}"
            pageAge.text = "${UserUtill.getAge(userCard.birth)}"
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PagerViewHoler {
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_card_item, parent, false)
        // 하위뷰까지 영향이 가도록 아웃 라인 설정
        view.clipToOutline = true
        indicator = view.findViewById(R.id.indicator)
        viewPager2 = view.findViewById(R.id.imgviewpager) as ViewPager2
        imgAdapter = UserImgViewAdapter(viewPager2, indicator)

        val scrollView = view.findViewById<ScrollView>(R.id.user_card_scroll)
        val rlSvBox = view.findViewById<RelativeLayout>(R.id.rl_sv_box)
        val drawBar = view.findViewById<LinearLayout>(R.id.LL_drawer_bar)

        drawBar.setOnTouchListener() { v, event ->
            Log.d("View_Drag:flow", "Drag Start Touch Listener ${event.action}")
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    cardStackView.requestDisallowInterceptTouchEvent(true)
                    isFlagScroll = true
                    scrollViewY = rlSvBox.y - event.rawY
                    Log.d("View_Drag:flow", "Drag Y :  ${scrollViewY}")
                    Log.d("View_Drag:flow", "event.raw Y :  ${event.rawY}")
                }
                MotionEvent.ACTION_MOVE -> {
                    if(event.rawY > (760 - 30) )
                        rlSvBox.animate()
                            .y(event.rawY + scrollViewY)
                            .setDuration(0)
                            .start()
                }
                MotionEvent.ACTION_UP -> {
                    isFlagScroll = false
                    cardStackView.requestDisallowInterceptTouchEvent(false)
                }
            }
            return@setOnTouchListener true
        }

        //scrollView.visibility = View.GONE

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })


        scrollView.setOnTouchListener { v, event ->

            isFlagScroll
        }


        viewPager2.offscreenPageLimit = 4
        indicator.setViewPager(viewPager2)

        return PagerViewHoler(view)
    }


    override fun onBindViewHolder(holder: PagerViewHoler, position: Int) {

        val userCard = data.get(position)
        imgAdapter.setImgs(userCard.imgs)
        if(viewPager2.adapter == null) viewPager2.adapter = imgAdapter

        indicator.createIndicators(imgAdapter.itemCount, 0)

        holder.bind(userCard)
    }

    override fun getItemCount(): Int = data.size


}