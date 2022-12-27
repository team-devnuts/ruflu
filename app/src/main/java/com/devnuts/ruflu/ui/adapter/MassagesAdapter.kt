package com.devnuts.ruflu.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemLeftMessageBinding
import com.devnuts.ruflu.databinding.ItemRightMessageBinding
import com.devnuts.ruflu.ui.model.chat.ChatMessage
import de.hdodenhof.circleimageview.CircleImageView

class MassagesAdapter(
    private val massages: ArrayList<ChatMessage>,
    private val toUserName: String,
    val toUserImgUrl: String
) : RecyclerView.Adapter<MassagesAdapter.MassagesViewHolder>() {

    private lateinit var lBind: ItemLeftMessageBinding
    private lateinit var rBind: ItemRightMessageBinding

    inner class MassagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ChatMessage) {
            val userNm = itemView.findViewById<TextView>(R.id.tv_nickName)
            val msgTextView = itemView.findViewById<TextView>(R.id.tv_massage)
            val userImg: CircleImageView? = itemView.findViewById(R.id.img_user)
            msgTextView.text = data.msg
            userNm.text = data.userName
            userImg?.setImageURI(Uri.parse(data.imgUrl))
        }
    }

    fun addItem(data: ChatMessage) {
        massages.add(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassagesViewHolder {
        val view: View
        if (viewType == 1) {
            lBind = ItemLeftMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = lBind.root
        } else {
            rBind = ItemRightMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = rBind.root
        }
        return MassagesViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val youUserNm = massages[position].userName

        return when (youUserNm == toUserName) {
            true -> 1
            false -> 2
        }
    }

    override fun onBindViewHolder(holder: MassagesViewHolder, position: Int) {
        holder.bind(massages[position])
    }

    override fun getItemCount() = massages.size
}
