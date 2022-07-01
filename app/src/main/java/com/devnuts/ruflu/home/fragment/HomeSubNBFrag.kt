package com.devnuts.ruflu.home.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.home.fragment.adapter.NearByAdapter
import com.devnuts.ruflu.home.fragment.viewmodel.UserDtlSharedViewModel
import com.devnuts.ruflu.R
import com.devnuts.ruflu.home.ItemDecoration
import com.devnuts.ruflu.home.fragment.viewmodel.HomeSubNBViewModel

class HomeSubNBFrag : Fragment() {

    private val userDtlSharedViewModel: UserDtlSharedViewModel by viewModels<UserDtlSharedViewModel>()
    private val nbViewModel: HomeSubNBViewModel by viewModels<HomeSubNBViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var nearByAdapter: NearByAdapter
    private lateinit var callback: OnBackPressedCallback
    private lateinit var userDetailFragment : UserDetailFragment
    private lateinit var childFragmentTransaction : FragmentTransaction
    private lateinit var userDetailContainer : RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_sub_nb_fragment, container, false)
        userDetailContainer = view.findViewById<RelativeLayout>(R.id.user_detail_container)
        recyclerView = view.findViewById(R.id.nb_recview)
        recyclerView.layoutManager = GridLayoutManager(this.context, 3, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(ItemDecoration(requireActivity()))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        nbViewModel.nbUser.observe(viewLifecycleOwner, {
            changeNBUser()
        })

        userDtlSharedViewModel.nbUserDtl.observe(viewLifecycleOwner, {
            nbViewModel.changeRatingToUser(it.ratingStar, it)
        })
    }

    private fun changeNBUser() {
        if(recyclerView.adapter == null) {
            nearByAdapter = NearByAdapter(nbViewModel.nbUser)
            nearByAdapter.setItemClickListener(object : NearByAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    Log.d("HomeSubNBFrag", "open childfragment")
                    val userDtl = nbViewModel.getNBUser(position)
                    userDtlSharedViewModel.setUserDtl(userDtl)
                    userDetailFragment = UserDetailFragment()
                    childFragmentTransaction = childFragmentManager.beginTransaction()
                    childFragmentTransaction
                        .add(R.id.user_detail_container, userDetailFragment, "child")
                        .addToBackStack(null)
                        .commit()

                    userDetailContainer.visibility = View.VISIBLE
                }
            })
        }

        recyclerView.adapter = nearByAdapter

        nearByAdapter.notifyDataSetChanged()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("UserDetailFragment", "onBackPressedCallback")
                childFragmentManager.beginTransaction().remove(userDetailFragment).commit()

                userDetailContainer.visibility = View.GONE
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}
