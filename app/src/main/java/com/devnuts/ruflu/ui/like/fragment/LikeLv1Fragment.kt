package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.RufluLikeLv1FragmentBinding
import com.devnuts.ruflu.ui.adapter.LikeLv1Adapter
import com.devnuts.ruflu.ui.like.viewmodel.LikeSubSEViewModel
import com.devnuts.ruflu.ui.like.viewmodel.LikeSESharedViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl
import timber.log.Timber

class LikeLv1Fragment : Fragment() {
    private lateinit var adapter: LikeLv1Adapter
    private lateinit var binding: RufluLikeLv1FragmentBinding
    private lateinit var callback: OnBackPressedCallback
    private lateinit var likeUserDetailFragment: LikeUserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var userDetailContainer: RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: LikeSESharedViewModel by viewModels()
    private val likeSubSEViewModel: LikeSubSEViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RufluLikeLv1FragmentBinding.inflate(inflater, container, false)
        userDetailContainer = binding.userDetailContainer
        recyclerView = binding.rufluSeRecycle
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        likeSubSEViewModel.lv1User.observe(viewLifecycleOwner) {
            changeAdapter()
        }
    }

    private fun changeAdapter() {
        if (recyclerView.adapter == null) {
            adapter = createAdapter()
            initAdapterListener()
            val helper = ItemTouchHelper(LikeTouchHelperCallback(adapter))
            helper.attachToRecyclerView(recyclerView)
        }

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initAdapterListener() {
        adapter.setItemClickListener(object : LikeLv1Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val userDtl = likeSubSEViewModel.getSeLikeLv1User(position)
                if (userDtl != null)
                    sharedViewModel.setUserDtl(userDtl)

                likeUserDetailFragment = LikeUserDetailFragment()
                childFragmentTransaction = childFragmentManager.beginTransaction()
                childFragmentTransaction
                    .add(R.id.user_detail_container, likeUserDetailFragment, "child")
                    .addToBackStack(null)
                    .commit()

                userDetailContainer.visibility = View.VISIBLE
            }
        })

        adapter.setItemSwipeListener(object : LikeLv1Adapter.OnItemSwipeListener {
            override fun onSwipe(user: UserDtl, direction: Int) {
                // 32 right 좋아요
                // 16 left  싫어요
                Timber.tag("onSwipe").d("direction :  $direction")

                if (direction == 32) {
                    likeSubSEViewModel.insertSeLikeLv2(user.user_id)
                }
            }
        })
    }

    private fun createAdapter(): LikeLv1Adapter {
        return LikeLv1Adapter(likeSubSEViewModel.lv1User.value!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.d("onBackPressedCallback")
                childFragmentManager.beginTransaction().remove(likeUserDetailFragment).commit()

                userDetailContainer.visibility = View.GONE
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}
