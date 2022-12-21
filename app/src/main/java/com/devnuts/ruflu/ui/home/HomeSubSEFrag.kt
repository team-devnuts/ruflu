package com.devnuts.ruflu.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.devnuts.ruflu.databinding.HomeSubSeFragmentBinding
import com.devnuts.ruflu.ui.adapter.UserCardViewAdapter
import com.devnuts.ruflu.ui.home.viewmodel.HomeSubSEViewModel
import com.devnuts.ruflu.ui.model.home.UserCard
import com.devnuts.ruflu.R
import com.yuyakaido.android.cardstackview.*

class HomeSubSEFrag() : Fragment() {

    val viewModel: HomeSubSEViewModel by viewModels()

    private lateinit var cardStackLayoutManager: CardStackLayoutManager
    lateinit var userCardViewAdapter: UserCardViewAdapter
    lateinit var cardStackView: CardStackView

    private val binding get() = _binding!!
    private var _binding : HomeSubSeFragmentBinding? = null
    private var cardPosition : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeSubSeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        init(view)
        setUpCardStack()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    // 카트스택 뷰와 레이아웃 매니저 생성 메소드
    private fun init(view:View) {
        cardStackView = view.findViewById<CardStackView>(R.id.user_card_recycler)
        cardStackLayoutManager = getInitCardStackManager()
        setBtnClick(binding.cardHateBtn, Direction.Left)
        setBtnClick(binding.cardSkipBtn, Direction.Bottom)
        setBtnClick(binding.cardLikeBtn, Direction.Right)
    }

    private fun setBtnClick(btn : Button, direction : Direction) {
        btn.setOnClickListener {
            cardStackLayoutManager.setSwipeAnimationSetting(getSetting(direction))
            cardStackView.swipe()
        }

    }
    // viewModel 초기화 메소드
    private fun initViewModel() {
        viewModel.userCard.observe(this.viewLifecycleOwner, Observer<ArrayList<UserCard>> {
            changeCardView()
        })
    }
    // 카드뷰 update 메소드
    private fun changeCardView() {
        if(cardStackView.adapter == null) {
            userCardViewAdapter = UserCardViewAdapter(viewModel, this, cardStackView)

        }
        cardStackView.adapter = userCardViewAdapter

        //userCardViewAdapter.notifyDataSetChanged()
    }

    // 카드뷰 초기 셋팅
    private fun setUpCardStack() {
        val setting = getSetting(Direction.Right)

        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackLayoutManager.setSwipeThreshold(0.3f)
        cardStackLayoutManager.setVisibleCount(2)
        cardStackLayoutManager.setTranslationInterval(8.0f)

        // card view를 만들어서 생성
        cardStackView.layoutManager = cardStackLayoutManager
        cardStackView.itemAnimator = DefaultItemAnimator()

    }

    private fun getSetting(direction : Direction): SwipeAnimationSetting {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        return setting
    }

    // 레이아웃 메니저 생성 메소드
    private fun getInitCardStackManager() : CardStackLayoutManager {

        return CardStackLayoutManager(this.context, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")
            }

            override fun onCardSwiped(direction: Direction?) {
                Log.d("CardStackView", "onCardSwiped: p = ${cardStackLayoutManager.topPosition}, d = $direction")

                if(direction == Direction.Right) {
                    ///viewModel.likeYourUserCard(cardPosition)
                } else if (direction == Direction.Left){
                    ///viewModel.hateYourUserCard(cardPosition)
                }

                if(cardStackLayoutManager.topPosition == cardStackLayoutManager.itemCount) {
                    Log.d("CardStackView", "refreshUserCard : topPos[${cardStackLayoutManager.topPosition}], itemCnt[${cardStackLayoutManager.itemCount}] ")
                    Thread.sleep(1000)
                    viewModel.loadUserCard()
                }
            }

            override fun onCardRewound() {
                Log.d("CardStackView", "onCardRewound: ${cardStackLayoutManager.topPosition}")
            }

            override fun onCardCanceled() {
                Log.d("CardStackView", "onCardCanceled: ${cardStackLayoutManager.topPosition}")
            }

            override fun onCardAppeared(view: View?, position: Int) {
                val textView = view!!.findViewById<TextView>(R.id.card_view_name)
                Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
               // cardPosition = position
            }

            override fun onCardDisappeared(view: View?, position: Int) {
                Log.d("CardStackView", "onCardDisappeared: ($position) ")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}