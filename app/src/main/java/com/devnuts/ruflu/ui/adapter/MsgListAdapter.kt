package com.devnuts.ruflu.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.model.ChatMessage
import com.devnuts.ruflu.databinding.MsgItemLeftBinding
import com.devnuts.ruflu.databinding.MsgItemRightBinding
import de.hdodenhof.circleimageview.CircleImageView

class MsgListAdapter(val msgList : ArrayList<ChatMessage>, val toUserName:String, val toUserImgUrl: String)
    : RecyclerView.Adapter<MsgListAdapter.MsgListViewHoler>() {

    private lateinit var lBind : MsgItemLeftBinding
    private lateinit var rBind : MsgItemRightBinding

    inner class MsgListViewHoler(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : ChatMessage) {
            val userNm = itemView.findViewById<TextView>(R.id.textv_nicname)
            val msgTextView = itemView.findViewById<TextView>(R.id.textv_msg)
            val userImg : CircleImageView? = itemView.findViewById(R.id.imgv)
            msgTextView.setText(data.msg)
            userNm.setText(data.userName)
            userImg?.setImageURI(Uri.parse(data.imgUrl))
        }
    }

    fun addItem(data : ChatMessage) {
        msgList.add(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgListViewHoler {
        var view : View? = null
        if(viewType == 1) {
            lBind = MsgItemLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = lBind.root
        } else {
            rBind = MsgItemRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = rBind.root
        }
        return MsgListViewHoler(view)
    }


    override fun getItemViewType(position: Int): Int {
        val youUserNm = msgList[position].userName
        return if(youUserNm.equals(toUserName)) {1} else {2}
    }

    override fun onBindViewHolder(holder: MsgListViewHoler, position: Int) {
        holder.bind(msgList[position])
    }

    override fun getItemCount() = msgList.size

}