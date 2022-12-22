package com.devnuts.ruflu.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.MsgItemLeftBinding
import com.devnuts.ruflu.databinding.MsgItemRightBinding
import com.devnuts.ruflu.ui.model.chat.ChatMessage
import de.hdodenhof.circleimageview.CircleImageView

class MsgListAdapter(
    private val msgList: ArrayList<ChatMessage>,
    private val toUserName: String,
    val toUserImgUrl: String
) : RecyclerView.Adapter<MsgListAdapter.MsgListViewHolder>() {

    private lateinit var lBind: MsgItemLeftBinding
    private lateinit var rBind: MsgItemRightBinding

    inner class MsgListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ChatMessage) {
            val userNm = itemView.findViewById<TextView>(R.id.textv_nicname)
            val msgTextView = itemView.findViewById<TextView>(R.id.textv_msg)
            val userImg: CircleImageView? = itemView.findViewById(R.id.imgv)
            msgTextView.text = data.msg
            userNm.text = data.userName
            userImg?.setImageURI(Uri.parse(data.imgUrl))
        }
    }

    fun addItem(data: ChatMessage) {
        msgList.add(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgListViewHolder {
        val view: View
        if (viewType == 1) {
            lBind = MsgItemLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = lBind.root
        } else {
            rBind = MsgItemRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = rBind.root
        }
        return MsgListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val youUserNm = msgList[position].userName

        return when(youUserNm == toUserName) {
            true -> 1
            false -> 2
        }
    }

    override fun onBindViewHolder(holder: MsgListViewHolder, position: Int) {
        holder.bind(msgList[position])
    }

    override fun getItemCount() = msgList.size
}
