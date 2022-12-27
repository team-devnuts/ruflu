package com.devnuts.ruflu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.databinding.ItemChatListBinding
import com.devnuts.ruflu.ui.model.chat.ChatRoom

class ChatAdapter(
    private val myRoomList: ArrayList<ChatRoom>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private lateinit var binding: ItemChatListBinding
    private lateinit var someClickListener: OnItemClickListener

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        binding = ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root

        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind()
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.someClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = myRoomList.size
}
