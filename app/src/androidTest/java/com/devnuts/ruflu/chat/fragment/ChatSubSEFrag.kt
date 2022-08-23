package com.devnuts.ruflu.chat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.chat.fragment.adapter.ChatSubSEAdapter
import com.devnuts.ruflu.chat.fragment.viewmodel.ChatSharedViewModel
import com.devnuts.ruflu.chat.fragment.viewmodel.ChatSubSEViewModel
import com.devnuts.ruflu.databinding.ChatSubSeFragmentBinding

class ChatSubSEFrag : Fragment() {

    companion object {
        fun newInstance() = ChatSubSEFrag()
    }

    private val viewModel: ChatSubSEViewModel by viewModels()
    private val sharedViewModel : ChatSharedViewModel by viewModels()
    private lateinit var bind : ChatSubSeFragmentBinding
    private lateinit var recycler : RecyclerView
    private lateinit var adapter : ChatSubSEAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myRoomContainer : RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = ChatSubSeFragmentBinding.inflate(inflater, container, false)
        val view = bind.root
        recycler = bind.chatRoomRecycler
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        myRoomContainer = bind.myRoomContainer
        recycler.layoutManager = layoutManager

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        viewModel.chatRoomList.observe(viewLifecycleOwner, {
            changeAdapter()
        })
    }

    private fun changeAdapter() {
        val chatRoomList = viewModel.chatRoomList.value
        if (chatRoomList != null) {
            adapter = ChatSubSEAdapter(chatRoomList)
            initAdapterListener()
        }
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun initAdapterListener() {
        adapter.setItemClickListener(object : ChatSubSEAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val chatRoom = viewModel.getChatRoomByPos(position)
                if(chatRoom != null) {
                    sharedViewModel.setMyRoom(chatRoom)
                    val myRoom = ChatRoomSubSEFrag()

                    val childFragmentTransaction = childFragmentManager.beginTransaction()
                    childFragmentTransaction
                        .add(R.id.my_room_container, myRoom, "child")
                        .addToBackStack(null)
                        .commit()

                    myRoomContainer.visibility = View.VISIBLE
                }
            }
        })
    }
}