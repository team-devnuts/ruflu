package com.devnuts.ruflu.chat.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.chat.fragment.model.ChatRoom
import com.devnuts.ruflu.databinding.ChatListItemBinding
import com.devnuts.ruflu.databinding.MyChatRoomBinding

class ChatSubSEAdapter(
        private val myRoomList : ArrayList<ChatRoom>
) : RecyclerView.Adapter<ChatSubSEAdapter.ChatSubSEViewHolder>() {
    private lateinit var bind: ChatListItemBinding
    private lateinit var seLikeLv1OnClickListener: OnItemClickListener


    inner class ChatSubSEViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind() {

        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatSubSEViewHolder {
        bind = ChatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = bind.root


        return ChatSubSEViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatSubSEViewHolder, position: Int) {

        holder.bind()
    }

    fun setItemClickListener(onItemClickListener: ChatSubSEAdapter.OnItemClickListener) {
        this.seLikeLv1OnClickListener = onItemClickListener
    }

    override fun getItemCount():Int = myRoomList.size
}