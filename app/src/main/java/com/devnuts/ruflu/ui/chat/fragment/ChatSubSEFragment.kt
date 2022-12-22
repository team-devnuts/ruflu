package com.devnuts.ruflu.ui.chat.fragment

import com.devnuts.ruflu.ui.chat.viewmodel.ChatSubSEViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentChatSubSeBinding
import com.devnuts.ruflu.ui.adapter.ChatSubSEAdapter
import com.devnuts.ruflu.ui.chat.viewmodel.ChatSharedViewModel

class ChatSubSEFragment : Fragment() {
    private lateinit var bind: FragmentChatSubSeBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ChatSubSEAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myRoomContainer: RelativeLayout
    private val viewModel: ChatSubSEViewModel by viewModels()
    private val sharedViewModel: ChatSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentChatSubSeBinding.inflate(inflater, container, false)
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
        viewModel.chatRoomList.observe(viewLifecycleOwner) { changeAdapter() }
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
        adapter.setItemClickListener(object : ChatSubSEAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val chatRoom = viewModel.getChatRoomByPos(position)
                if (chatRoom != null) {
                    sharedViewModel.setMyRoom(chatRoom)
                    val myRoom = ChatRoomSubSEFragment()

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

    companion object {
        fun newInstance() = ChatSubSEFragment()
    }
}
