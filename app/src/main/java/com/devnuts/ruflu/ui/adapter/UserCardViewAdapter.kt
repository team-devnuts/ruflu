package com.devnuts.ruflu.ui.adapter

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
import com.devnuts.ruflu.comm.utill.UserUtil
import com.devnuts.ruflu.ui.home.fragment.HomeSubSEFrag
import com.devnuts.ruflu.ui.home.viewmodel.HomeSubSEViewModel
import com.devnuts.ruflu.ui.model.home.UserCard
import com.yuyakaido.android.cardstackview.CardStackView
import me.relex.circleindicator.CircleIndicator3
import timber.log.Timber

class UserCardViewAdapter(
    private val viewModel: HomeSubSEViewModel,
    val fragment: HomeSubSEFrag,
    private val cardStackView: CardStackView
) : RecyclerView.Adapter<UserCardViewAdapter.PagerViewHolder>() {

    private lateinit var view: View
    private lateinit var imgAdapter: UserImgViewAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private val animation =
        AnimationUtils.loadAnimation(fragment.context, R.anim.user_card_drawer_action)

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageName: TextView = itemView.findViewById(R.id.card_view_name)
        private val pageAge: TextView = itemView.findViewById(R.id.card_view_age)

        fun bind(userCard: UserCard) {
            pageName.text = userCard.nick_nm
            pageAge.text = "${UserUtil.getAge(userCard.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_card_item, parent, false)

        indicator = view.findViewById(R.id.indicator)
        viewPager2 = view.findViewById(R.id.imgviewpager) as ViewPager2
        imgAdapter = UserImgViewAdapter(viewPager2, indicator)
        val drawerBar = view.findViewById<LinearLayout>(R.id.drawer_bar)
        val scrollView = view.findViewById<ScrollView>(R.id.user_card_scroll)

        scrollView.visibility = View.GONE

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        drawerBar.setOnClickListener(View.OnClickListener {

            val sVisbSt = scrollView.visibility
            if (sVisbSt == View.GONE) {
                scrollView.visibility = View.VISIBLE
            } else {
                scrollView.visibility = View.GONE
            }
            Timber.tag("drawerBar").d("hee %s", scrollView.visibility)

            drawerBar.startAnimation(animation)
            scrollView.startAnimation(animation)
        })

        scrollView.setOnTouchListener { v, event ->
            cardStackView.requestDisallowInterceptTouchEvent(true)

            return@setOnTouchListener false
        }

        viewPager2.offscreenPageLimit = 4
        indicator.setViewPager(viewPager2)

        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val userCard = viewModel.userCard.value!!.get(position)
        imgAdapter.setImages(userCard.imgs)

        if (viewPager2.adapter == null) viewPager2.adapter = imgAdapter
        Timber.d("onCreateViewHolder $position nickNm ${userCard.nick_nm} age ${userCard.birth} ")

        indicator.createIndicators(imgAdapter.itemCount, 0)
        holder.bind(userCard)
    }

    override fun getItemCount(): Int = viewModel.userCard.value!!.size
}
