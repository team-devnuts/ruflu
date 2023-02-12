package com.devnuts.ruflu.ui.some.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentLikeBinding
import com.devnuts.ruflu.ui.adapter.SwipeAdapter
import com.devnuts.ruflu.ui.common.UserDetailFragment
import com.devnuts.ruflu.ui.some.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.some.viewmodel.LikeViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LikeFragment : Fragment() {
    /* 정리 필요 */
    private lateinit var userDetailFragment: UserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var callback: OnBackPressedCallback

    private var _binding: FragmentLikeBinding? = null
    val binding get() = _binding!!
    private val likeViewModel: LikeViewModel by viewModels()

    private val likeAdapter: SwipeAdapter<Model> by lazy {
        SwipeAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                userDetailFragment = UserDetailFragment(model as UserUIModel)

                childFragmentTransaction = childFragmentManager.beginTransaction()
                childFragmentTransaction
                    .add(R.id.rl_user_detail, userDetailFragment, "child")
                    .addToBackStack(null)
                    .commit()

                binding.rlUserDetail.visibility = View.VISIBLE
            }

            override fun onTouch(view: View, model: Model, event: MotionEvent) {}

            override fun onSwipe(position: Int, direction: Int) {
                /** 32 right 좋아요, 16 left  싫어요 **/
                if (direction == 32) {
                    likeViewModel.addUserInMyMatchList(likeViewModel.getSomeUser(position)!!.userId)
                }
                // 현재 임시
                //someViewModel.userInfo.value?.toMutableList()?.remove(someViewModel.getSomeUser(position))
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initObserve()
    }

    private fun setupAdapter() {
        binding.rvLike.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvLike.adapter = likeAdapter
        val helper = ItemTouchHelper(SomeTouchHelperCallback(likeAdapter))
        helper.attachToRecyclerView(binding.rvLike)
    }

    private fun initObserve() {
        this.lifecycleScope.launch {
            likeViewModel.getLikeMeUserList()
        }

        this.lifecycleScope.launch {
            likeViewModel.userInfo.collect {
                val model = it as List<Model>
                likeAdapter.submitList(model)
            }
        }
    }

    /* 정리 필요 */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.d("onBackPressedCallback")
                childFragmentManager.beginTransaction().remove(userDetailFragment).commit()

                binding.rlUserDetail.visibility = View.GONE
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
