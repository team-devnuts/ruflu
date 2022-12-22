package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.RufluLikeLv2FragmentBinding
import com.devnuts.ruflu.ui.adapter.LikeLv2Adapter
import com.devnuts.ruflu.ui.like.viewmodel.LikeSubSEViewModel
import com.devnuts.ruflu.ui.like.viewmodel.LikeSESharedViewModel

class LikeLv2Fragment : Fragment() {
    private lateinit var likeLv2Binding: RufluLikeLv2FragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userDetailContainer: RelativeLayout
    private lateinit var likeUserDetailFragment: LikeUserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var adapter: LikeLv2Adapter
    private val viewModel: LikeSubSEViewModel by viewModels()
    private val sharedViewModel: LikeSESharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        likeLv2Binding = RufluLikeLv2FragmentBinding.inflate(inflater, container, false)
        recyclerView = likeLv2Binding.rufluSeRecycle2
        userDetailContainer = likeLv2Binding.userDetailContainer
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        return likeLv2Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        sharedViewModel.userDtl.observe(viewLifecycleOwner) {
            changeAdapter()
        }

        viewModel.lv2User.observe(viewLifecycleOwner) {
            changeAdapter()
        }
    }

    private fun changeAdapter() {
        if (recyclerView.adapter == null) {
            adapter = createAdapter()
            initAdapterListener()
        }

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initAdapterListener() {
        adapter.setUserClickListener(object : LikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val userDtl = viewModel.getSeLikeLv2User(position)
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

        adapter.setTalkClickListener(object : LikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val user = viewModel.lv2User.value?.get(position)
                viewModel.sendMessageAskingTalkToUser(user)
            }
        })

        adapter.setCancelClickListener(object : LikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val user = viewModel.lv2User.value?.get(position)
                viewModel.removeLikeUser(user)
            }
        })
    }

    private fun createAdapter(): LikeLv2Adapter {
        return LikeLv2Adapter(viewModel.lv2User.value!!)
    }
}
