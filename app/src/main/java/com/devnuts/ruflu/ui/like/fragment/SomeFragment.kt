package com.devnuts.ruflu.ui.like.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentSomeBinding
import com.devnuts.ruflu.ui.adapter.SomeAdapter
import com.devnuts.ruflu.ui.like.listener.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.like.viewmodel.LikeSharedViewModel
import com.devnuts.ruflu.ui.like.viewmodel.SomeViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl
import timber.log.Timber

class SomeFragment : Fragment() {
    private lateinit var viewModel: SomeViewModel
    private lateinit var adapter: SomeAdapter
    private lateinit var binding: FragmentSomeBinding
    private lateinit var callback: OnBackPressedCallback
    private lateinit var likeUserDetailFragment: LikeUserDetailFragment
    private lateinit var childFragmentTransaction: FragmentTransaction
    private lateinit var userDetailContainer: RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: LikeSharedViewModel by viewModels()
    private val someViewModel: SomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSomeBinding.inflate(inflater, container, false)
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
        someViewModel.someUser.observe(viewLifecycleOwner) {
            changeAdapter()
        }
    }

    private fun changeAdapter() {
        if (recyclerView.adapter == null) {
            adapter = createAdapter()
            initAdapterListener()
            val helper = ItemTouchHelper(SomeTouchHelperCallback(adapter))
            helper.attachToRecyclerView(recyclerView)
        }

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initAdapterListener() {
        adapter.setItemClickListener(object : SomeAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

                val userDtl = someViewModel.getSomeUser(position)
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

        adapter.setItemSwipeListener(object : SomeAdapter.OnItemSwipeListener {
            override fun onSwipe(user: UserDtl, direction: Int) {
                // 32 right 좋아요
                // 16 left  싫어요
                Timber.tag("onSwipe").d("direction :  $direction")

                if (direction == 32) {
                    someViewModel.insertSeLikeLv2(user.user_id)
                }
            }
        })
    }

    private fun createAdapter(): SomeAdapter {
        return SomeAdapter(someViewModel.someUser.value!!)
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
