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
import com.devnuts.ruflu.ui.adapter.RufluLikeLv2Adapter
import com.devnuts.ruflu.ui.like.viewmodel.RufluSubSEViewModel
import com.devnuts.ruflu.ui.like.viewmodel.SESharedViewModel

class RufluLikeLv2Fragment : Fragment() {

    private lateinit var rufluLv2Binding: RufluLikeLv2FragmentBinding
    private val viewModel: RufluSubSEViewModel by viewModels<RufluSubSEViewModel>()
    private val sharedViewModel: SESharedViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userDetailContainer: RelativeLayout
    private lateinit var rufluUserDetailFragment: RufluUserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var adapter: RufluLikeLv2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rufluLv2Binding = RufluLikeLv2FragmentBinding.inflate(inflater, container, false)
        val view = rufluLv2Binding.root
        recyclerView = rufluLv2Binding.rufluSeRecycle2
        userDetailContainer = rufluLv2Binding.userDetailContainer
        var layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        sharedViewModel.userDtl.observe(viewLifecycleOwner, {
            changeAdapter()
        })

        viewModel.lv2User.observe(viewLifecycleOwner, {
            changeAdapter()
        })
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
        adapter.setUserClickListener(object : RufluLikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val userDtl = viewModel.getSeLikeLv2User(position)
                if (userDtl != null)
                    sharedViewModel.setUserDtl(userDtl)

                rufluUserDetailFragment = RufluUserDetailFragment()
                childFragmentTransaction = childFragmentManager.beginTransaction()
                childFragmentTransaction
                        .add(R.id.user_detail_container, rufluUserDetailFragment, "child")
                        .addToBackStack(null)
                        .commit()

                userDetailContainer.visibility = View.VISIBLE
            }
        })

        adapter.setTalkClickListener(object : RufluLikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val user = viewModel.lv2User.value?.get(position)
                viewModel.sendMessageAskingTalkToUser(user)
            }
        })

        adapter.setCancelClickListener(object : RufluLikeLv2Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val user = viewModel.lv2User.value?.get(position)
                viewModel.removeLikeUser(user)
            }
        })
    }

    private fun createAdapter(): RufluLikeLv2Adapter {
        return RufluLikeLv2Adapter(viewModel.lv2User.value!!)
    }
}
