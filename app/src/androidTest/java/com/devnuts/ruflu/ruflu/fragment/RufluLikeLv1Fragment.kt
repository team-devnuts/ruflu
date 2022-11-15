package com.devnuts.ruflu.ruflu.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.RufluLikeLv1FragmentBinding
import com.devnuts.ruflu.home.fragment.RufluUserDetailFragment
import com.devnuts.ruflu.home.model.UserDtl
import com.devnuts.ruflu.ruflu.fragment.adapter.RufluLikeLv1Adapter
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv1User
import com.devnuts.ruflu.ruflu.fragment.viewmodel.RufluSubSEViewModel
import com.devnuts.ruflu.ruflu.fragment.viewmodel.SESharedViewModel

class RufluLikeLv1Fragment : Fragment() {

    private lateinit var adapter: RufluLikeLv1Adapter
    private lateinit var binding: RufluLikeLv1FragmentBinding
    private val sharedViewModel: SESharedViewModel by viewModels()
    private val rufluSubSEViewModel: RufluSubSEViewModel by viewModels<RufluSubSEViewModel>()
    private lateinit var callback: OnBackPressedCallback
    private lateinit var rufluUserDetailFragment : RufluUserDetailFragment
    private lateinit var childFragmentTransaction : FragmentTransaction
    private lateinit var userDetailContainer : RelativeLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = RufluLikeLv1FragmentBinding.inflate(inflater, container, false)
        var view = binding.root
        userDetailContainer = binding.userDetailContainer
        recyclerView = binding.rufluSeRecycle
        var layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        rufluSubSEViewModel.lv1User.observe(viewLifecycleOwner, {
            changeAdapter()
        })
    }
    private fun changeAdapter() {
        if(recyclerView.adapter == null) {
            adapter = createAdapter()
            initAdapterListener()
            val helper = ItemTouchHelper(RufluTouchHelperCallback(adapter))
            helper.attachToRecyclerView(recyclerView)
        }

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initAdapterListener() {
        adapter.setItemClickListener(object : RufluLikeLv1Adapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val userDtl = rufluSubSEViewModel.getSeLikeLv1User(position)
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

        adapter.setItemSwipeListener(object : RufluLikeLv1Adapter.OnItemSwipeListener {
            override fun onSwipe(user: UserDtl, direction: Int) {
                //32 right 좋아요
                //16 left  싫어요
                Log.d("onSwipe","direction :  ${direction}")
                if (direction == 32) {
                    rufluSubSEViewModel.insertSeLikeLv2(user.user_id)
                } else {

                }
            }

        })
    }

    private fun createAdapter(): RufluLikeLv1Adapter{
        return RufluLikeLv1Adapter(rufluSubSEViewModel.lv1User.value!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("UserDetailFragment", "onBackPressedCallback")
                childFragmentManager.beginTransaction().remove(rufluUserDetailFragment).commit()

                userDetailContainer.visibility = View.GONE
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}