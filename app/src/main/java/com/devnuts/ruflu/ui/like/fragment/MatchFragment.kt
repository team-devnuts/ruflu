package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentSomeBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.adapter.SwipeAdapter
import com.devnuts.ruflu.ui.common.UserDetailFragment
import com.devnuts.ruflu.ui.like.listener.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.like.viewmodel.MatchViewModel
import com.devnuts.ruflu.ui.like.viewmodel.SomeViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener
import timber.log.Timber

class MatchFragment : Fragment() {
    /* 정리 필요 */
    private lateinit var userDetailFragment: UserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var callback: OnBackPressedCallback

    private var _binding: FragmentSomeBinding? = null
    val binding get() = _binding!!
    private val matchViewModel: MatchViewModel by viewModels()

    private val matchAdapter : ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener{
            override fun onClick(view: View, model: Model, position: Int) {
                userDetailFragment = UserDetailFragment(model as UserUIModel)

                childFragmentTransaction = childFragmentManager.beginTransaction()
                childFragmentTransaction
                    .add(R.id.rl_user_detail, userDetailFragment, "child")
                    .addToBackStack(null)
                    .commit()

                binding.rlUserDetail.visibility = View.VISIBLE
            }

            override fun onTouch(view: View, model: Model, event: MotionEvent) {
                TODO("Not yet implemented")
            }

            override fun onSwipe(position: Int, direction: Int) {
                TODO("Not yet implemented")
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
        binding.rvSome.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvSome.adapter = matchAdapter
    }

    private fun initObserve() {
        matchViewModel.matchUser.observe(viewLifecycleOwner) {
            val model = it as List<Model>
            matchAdapter.submitList(model)
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
