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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.databinding.FragmentCardBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.home.viewmodel.CardViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.worker.CustomCardStackView
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
class CardFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CardViewModel by viewModels()

    private lateinit var cardStackLayoutManager: CardStackLayoutManager
    private var touchDwX: Float = 0f
    private var touchDwY: Float = 0f
    private var cardPosition: Int = 0

    private val cardAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        viewModel.getUserCard()
        initObserve()

    }

    // 카트스택 뷰와 레이아웃 매니저 생성 메소드
    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        with(binding) {
            cardStackLayoutManager = initLayoutManager()
            rvCard.layoutManager = cardStackLayoutManager

            rvCard.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
            rvCard.setOnCustomSwipeTouchEvent(object :
                CustomCardStackView.OnCustomSwipeTouchEvent {
                override fun onCustomizedSwipe(event: MotionEvent?) {
                    onControlDirectionCardStackSwipe(event)
                }
            })

            cardHateBtn.visibility = View.GONE
            cardSkipBtn.visibility = View.GONE
            cardLikeBtn.visibility = View.GONE
            cardChatBtn.visibility = View.GONE

            setupButtonListener(cardHateBtn, Direction.Left)
            setupButtonListener(cardSkipBtn, Direction.Bottom)
            setupButtonListener(cardLikeBtn, Direction.Right)
        }
    }

    private fun setupButtonListener(btn: ImageButton, direction: Direction) {
        btn.setOnClickListener {
            cardStackLayoutManager.setSwipeAnimationSetting(getSwipeSetting(direction))
            binding.rvCard.swipe()
        }
    }

    private fun initObserve() {
        this.lifecycleScope.launch {
            viewModel.cardUiState.collect {
                cardAdapter.submitList(it)
            }
        }
    }

    // 리사이클러 뷰 초기화
    private fun initView() = binding.let {
        it.rvCard.itemAnimator = null
        it.rvCard.adapter = cardAdapter
    }

    private fun getSwipeSetting(direction: Direction): SwipeAnimationSetting {
        return SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
    }

    /* 미사용 */
    private fun getRewindSetting(direction: Direction): RewindAnimationSetting {
        return RewindAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(DecelerateInterpolator())
            .build()
    }

    // 레이아웃 메니저 생성 메소드
    private fun initLayoutManager(): CardStackLayoutManager {
        val layoutManager =  CardStackLayoutManager(this.context, object : CardStackListener {
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
                        viewModel.addLikeUser(cardPosition)
                    }
                    Direction.Left -> {
                        viewModel.addHateUser(cardPosition)
                    }
                    else -> {}
                }
                Log.d(
                    "CardStackView",
                    "refreshUserCard : topPos[${cardStackLayoutManager.topPosition}], itemCnt[${cardStackLayoutManager.itemCount}] "
                )
                if (cardStackLayoutManager.topPosition == cardStackLayoutManager.itemCount) {

                    Thread.sleep(1000)
                    viewModel.getUserCard()
                }
            }

            override fun onCardRewound() {}

            override fun onCardCanceled() {}

            override fun onCardAppeared(view: View?, position: Int) {}

            override fun onCardDisappeared(view: View?, position: Int) {}
        })

        with(layoutManager) {
            setSwipeAnimationSetting(getSwipeSetting(Direction.Right))
            setDirections(Direction.FREEDOM)
            setSwipeThreshold(0.3f)
            setVisibleCount(2)
            setTranslationInterval(0.0f)
            setScaleInterval(0.0f)
            setMaxDegree(0.0f)
            setCanScrollHorizontal(false)
            setCanScrollVertical(false)
        }
        return layoutManager
    }

    private fun onControlDirectionCardStackSwipe(event: MotionEvent?) {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                touchDwX = event.x
                touchDwY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val moveTouchX = touchDwX - event.x
                val moveTouchY = touchDwY - event.y

                var direction: Direction? = null

                if (abs(moveTouchX) > abs(moveTouchY)) {
                    if (moveTouchX < -100) direction = Direction.Right
                    else if (moveTouchX > 100) direction = Direction.Left
                } else {
                    if (moveTouchY > 100) direction = Direction.Top
                }

                if (direction != null) {
                    cardStackLayoutManager.setSwipeAnimationSetting(getSwipeSetting(direction))
                    binding.rvCard.swipe()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
