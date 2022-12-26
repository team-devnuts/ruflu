package com.devnuts.ruflu.ui.chat.fragment

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
import com.devnuts.ruflu.databinding.FragmentChatBinding
import com.devnuts.ruflu.ui.adapter.ChatAdapter
import com.devnuts.ruflu.ui.chat.viewmodel.ChatSharedViewModel
import com.devnuts.ruflu.ui.chat.viewmodel.ChatViewModel

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ChatAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myRoomContainer: RelativeLayout
    private val viewModel: ChatViewModel by viewModels()
    private val sharedViewModel: ChatSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        recycler = binding.chatRoomRecycler
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        myRoomContainer = binding.myRoomContainer
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
            adapter = ChatAdapter(chatRoomList)
            initAdapterListener()
        }
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initAdapterListener() {
        adapter.setItemClickListener(object : ChatAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val chatRoom = viewModel.getChatRoomByPos(position)
                if (chatRoom != null) {
                    sharedViewModel.setMyRoom(chatRoom)
                    val myRoom = ChatRoomFragment()

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
