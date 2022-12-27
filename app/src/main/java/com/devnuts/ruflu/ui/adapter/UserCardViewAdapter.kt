package com.devnuts.ruflu.ui.adapter

import android.annotation.SuppressLint
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.home.fragment.CardFragment
import com.devnuts.ruflu.ui.model.home.UserCardUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.worker.CustomCardStackView
import java.util.*
import kotlin.collections.ArrayList
import me.relex.circleindicator.CircleIndicator3

class UserCardViewAdapter(
    val data: ArrayList<UserCardUIModel>,
    val fragment: CardFragment,
    private val cardStackView: CustomCardStackView
) : RecyclerView.Adapter<UserCardViewAdapter.UserCardViewHolder>() {

    private lateinit var view: View
    private lateinit var imgAdapter: UserImageViewAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicator: CircleIndicator3

    // private val animation = AnimationUtils.loadAnimation(fragment.context,R.anim.user_card_drawer_action)
    private var scrollViewY = 0f
    private var isFlagScroll = false

    inner class UserCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageName: TextView = itemView.findViewById(R.id.card_view_name)
        private val pageAge: TextView = itemView.findViewById(R.id.card_view_age)

        fun bind(userCard: UserCardUIModel) {
            pageName.text = userCard.nick_nm
            pageAge.text = "${UserUtil.getAge(userCard.birth)}"
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_card, parent, false)
        // 하위뷰까지 영향이 가도록 아웃 라인 설정
        view.clipToOutline = true
        indicator = view.findViewById(R.id.indicator)
        viewPager2 = view.findViewById(R.id.imgviewpager) as ViewPager2
        imgAdapter = UserImageViewAdapter(viewPager2, indicator)

        val scrollView = view.findViewById<ScrollView>(R.id.user_card_scroll)
        val rlSvBox = view.findViewById<RelativeLayout>(R.id.rl_sv_box)
        val drawBar = view.findViewById<LinearLayout>(R.id.LL_drawer_bar)

        drawBar.setOnTouchListener() { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    cardStackView.requestDisallowInterceptTouchEvent(true)
                    isFlagScroll = true
                    scrollViewY = rlSvBox.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.rawY > (760 - 30))
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

        // scrollView.visibility = View.GONE

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        scrollView.setOnTouchListener { _, _ -> isFlagScroll }

        viewPager2.offscreenPageLimit = 4
        indicator.setViewPager(viewPager2)

        return UserCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {

        val userCard = data.get(position)
        imgAdapter.setImages(userCard.imgs)
        if (viewPager2.adapter == null) viewPager2.adapter = imgAdapter

        indicator.createIndicators(imgAdapter.itemCount, 0)

        holder.bind(userCard)
    }

    override fun getItemCount(): Int = data.size
}
