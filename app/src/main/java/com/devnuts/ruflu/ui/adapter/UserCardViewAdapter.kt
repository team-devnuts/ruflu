package com.devnuts.ruflu.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtill
import com.devnuts.ruflu.ui.home.HomeSubSEFrag
import com.devnuts.ruflu.ui.home.viewmodel.HomeSubSEViewModel
import com.devnuts.ruflu.home.model.UserCard
import com.yuyakaido.android.cardstackview.CardStackView
import me.relex.circleindicator.CircleIndicator3

class UserCardViewAdapter(private val viewModel: HomeSubSEViewModel, val fragment: HomeSubSEFrag, val cardStackView: CardStackView)
    : RecyclerView.Adapter<UserCardViewAdapter.PagerViewHoler>(){

    private lateinit var view: View
    private lateinit var imgAdapter: UserImgViewAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private val animation = AnimationUtils.loadAnimation(fragment.context,R.anim.user_card_drawer_action)

    inner class PagerViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageName : TextView = itemView.findViewById(R.id.card_view_name)
        private val pageAge : TextView = itemView.findViewById(R.id.card_view_age)

        fun bind(userCard: UserCard) {
            pageName.text = "${userCard.nick_nm}"
            pageAge.text = "${UserUtill.getAge(userCard.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PagerViewHoler {
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_card_item, parent, false)

        indicator = view.findViewById(R.id.indicator)
        viewPager2 = view.findViewById(R.id.imgviewpager) as ViewPager2
        imgAdapter = UserImgViewAdapter(viewPager2, indicator)
        val drawer_bar = view.findViewById<LinearLayout>(R.id.drawer_bar)
        val scrollView = view.findViewById<ScrollView>(R.id.user_card_scroll)

        scrollView.visibility = View.GONE

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })

        drawer_bar.setOnClickListener(View.OnClickListener {

            val sVisbSt = scrollView.visibility
            if(sVisbSt == View.GONE) {
                scrollView.visibility = View.VISIBLE
            } else {
                scrollView.visibility = View.GONE
            }
            Log.d("drawer_bar", "hee " +  scrollView.visibility)

            drawer_bar.startAnimation(animation)
            scrollView.startAnimation(animation)


        })

        scrollView.setOnTouchListener { v, event ->
            cardStackView.requestDisallowInterceptTouchEvent(true)
            return@setOnTouchListener false
        }
        viewPager2.offscreenPageLimit = 4
        indicator.setViewPager(viewPager2)

        return PagerViewHoler(view)
    }


    override fun onBindViewHolder(holder: PagerViewHoler, position: Int) {

        val userCard = viewModel.userCard.value!!.get(position)
        imgAdapter.setImgs(userCard.imgs)
        if(viewPager2.adapter == null) viewPager2.adapter = imgAdapter
        Log.d("UserCardViewAdapter", "onCreateViewHolder ${position} nickNm ${userCard.nick_nm} age ${userCard.birth} ")
        indicator.createIndicators(imgAdapter.itemCount, 0)
        holder.bind(userCard)
    }

    override fun getItemCount(): Int = viewModel.userCard.value!!.size
}