package com.devnuts.ruflu.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentCardBinding
import com.devnuts.ruflu.ui.adapter.UserCardViewAdapter
import com.devnuts.ruflu.ui.home.viewmodel.CardViewModel
import com.devnuts.ruflu.ui.model.home.UserCardUIModel
import com.devnuts.ruflu.worker.CustomCardStackView
import com.yuyakaido.android.cardstackview.*
import kotlin.math.abs

class CardFragment : Fragment() {
    private lateinit var cardStackLayoutManager: CardStackLayoutManager
    private lateinit var userCardViewAdapter: UserCardViewAdapter
    private lateinit var cardStackView: CustomCardStackView
    private var touchDwX: Float = 0f
    private var touchDwY: Float = 0f
    private var cardPosition: Int = 0
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
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
    @SuppressLint("ClickableViewAccessibility")
    private fun init(view: View) {
        cardStackView = view.findViewById<CustomCardStackView>(R.id.user_card_recycler)
        cardStackView.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        cardStackView.setOnCustomSwipeTouchEvent(object :
            CustomCardStackView.OnCustomSwipeTouchEvent {
            override fun onCustomizedSwipe(event: MotionEvent?) {
                onControlDirectionCardStackSwipe(event)
            }
        })
        cardStackLayoutManager = getInitCardStackManager()
        binding.cardHateBtn.visibility = View.GONE
        binding.cardSkipBtn.visibility = View.GONE
        binding.cardLikeBtn.visibility = View.GONE
        binding.cardChatBtn.visibility = View.GONE

        setBtnClick(binding.cardHateBtn, Direction.Left)
        setBtnClick(binding.cardSkipBtn, Direction.Bottom)
        setBtnClick(binding.cardLikeBtn, Direction.Right)
    }

    private fun setBtnClick(btn: ImageButton, direction: Direction) {
        btn.setOnClickListener {
            cardStackLayoutManager.setSwipeAnimationSetting(getSwipeSetting(direction))
            cardStackView.swipe()
        }
    }

    // viewModel 초기화 메소드
    private fun initViewModel() {
        viewModel.userCard.observe(this.viewLifecycleOwner, Observer<ArrayList<UserCardUIModel>> {
            changeCardView()
        })
    }

    // 카드뷰 update 메소드
    private fun changeCardView() {
        if (cardStackView.adapter == null) {
            userCardViewAdapter =
                UserCardViewAdapter(viewModel.userCard.value!!, this, cardStackView)
        }
        cardStackView.adapter = userCardViewAdapter

        userCardViewAdapter.notifyDataSetChanged()
    }

    // 카드뷰 초기 셋팅
    private fun setUpCardStack() {
        val setting = getSwipeSetting(Direction.Right)

        cardStackLayoutManager.setSwipeAnimationSetting(setting)
        cardStackLayoutManager.setDirections(Direction.FREEDOM)
        cardStackLayoutManager.setSwipeThreshold(0.3f)
        cardStackLayoutManager.setVisibleCount(2)
        cardStackLayoutManager.setTranslationInterval(0.0f)
        cardStackLayoutManager.setScaleInterval(0.0f)
        cardStackLayoutManager.setMaxDegree(0.0f)
        cardStackLayoutManager.setCanScrollHorizontal(false)
        cardStackLayoutManager.setCanScrollVertical(false)

        // card view를 만들어서 생성
        cardStackView.layoutManager = cardStackLayoutManager
        cardStackView.itemAnimator = DefaultItemAnimator()
    }

    private fun getSwipeSetting(direction: Direction): SwipeAnimationSetting {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        return setting
    }

    private fun getRewindSetting(direction: Direction): RewindAnimationSetting {
        val setting = RewindAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(DecelerateInterpolator())
            .build()
        return setting
    }

    // 레이아웃 메니저 생성 메소드
    private fun getInitCardStackManager(): CardStackLayoutManager {

        return CardStackLayoutManager(this.context, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")
            }

            @SuppressLint("LogNotTimber")
            override fun onCardSwiped(direction: Direction?) {
                Log.d(
                    "CardStackView",
                    "onCardSwiped: p = ${cardStackLayoutManager.topPosition}, d = $direction"
                )

                when (direction) {
                    Direction.Right -> {
                        viewModel.likeYourUserCard(cardPosition)
                    }
                    Direction.Left -> {
                        // viewModel.hateYourUserCard(cardPosition)
                    }
                }
                Log.d(
                    "CardStackView",
                    "refreshUserCard : topPos[${cardStackLayoutManager.topPosition}], itemCnt[${cardStackLayoutManager.itemCount}] "
                )
                if (cardStackLayoutManager.topPosition == cardStackLayoutManager.itemCount) {

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

    private fun onControlDirectionCardStackSwipe(event: MotionEvent?) {
        val action = event?.action
        Log.d("CARDVIEW ACTION", " : $action")
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                touchDwX = event.x
                touchDwY = event.y
                Log.d("CARDVIEW ACTIONDOWN", "x = $touchDwX, y = $touchDwY")
            }
            MotionEvent.ACTION_UP -> {
                var moveTouchX = touchDwX - event.x
                var moveTouchY = touchDwY - event.y

                var direction: Direction? = null

                if (abs(moveTouchX) > abs(moveTouchY)) {
                    Log.d("CARDVIEWTOUCH_X", "$moveTouchX")
                    if (moveTouchX < -100) direction = Direction.Right
                    else if (moveTouchX > 100) direction = Direction.Left
                } else {
                    Log.d("CARDVIEWTOUCH_Y", "$moveTouchY")
                    if (moveTouchY < -100) else if (moveTouchY > 100) direction = Direction.Top
                }

                if (direction != null) {
                    cardStackLayoutManager.setSwipeAnimationSetting(getSwipeSetting(direction))
                    cardStackView.swipe()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
