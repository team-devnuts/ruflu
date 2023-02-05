package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentSomeBinding
import com.devnuts.ruflu.ui.adapter.SwipeAdapter
import com.devnuts.ruflu.ui.common.UserDetailFragment
import com.devnuts.ruflu.ui.like.listener.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.like.viewmodel.SomeViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SomeFragment : Fragment() {
    /* 정리 필요 */
    private lateinit var userDetailFragment: UserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var callback: OnBackPressedCallback

    private var _binding: FragmentSomeBinding? = null
    val binding get() = _binding!!
    private val someViewModel: SomeViewModel by viewModels()

    private val someAdapter: SwipeAdapter<Model> by lazy {
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
                    someViewModel.addUserInMyMatchList(someViewModel.getSomeUser(position)!!.userId)
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
        _binding = FragmentSomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initObserve()
    }

    private fun setupAdapter() {
        binding.rvSome.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvSome.adapter = someAdapter
        val helper = ItemTouchHelper(SomeTouchHelperCallback(someAdapter))
        helper.attachToRecyclerView(binding.rvSome)
    }

    private fun initObserve() {
        Log.d("flow", "SomeFragment IN!!!!!!")
        this.lifecycleScope.launch {
            someViewModel.getLikeMeUserList()
        }

        this.lifecycleScope.launch {
            someViewModel.userInfo.collect {
                //Log.d("flow", "${it.get(0).type}")
                val model = it as List<Model>
                someAdapter.submitList(model)
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
