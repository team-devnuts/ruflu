package com.devnuts.ruflu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.ui.model.home.UserDtl

class NearByAdapter(val data: MutableLiveData<ArrayList<UserDtl>>) :
    RecyclerView.Adapter<NearByAdapter.NearByViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var view: View

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    inner class NearByViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(images: List<String>) {
            val imgView = view.findViewById<ImageView>(R.id.nbUPfImg)

            if (images.isNotEmpty())
                UserUtil.setImageWithGlide(view, images[0], imgView)
            else imgView.setImageResource(R.drawable.noimg_fac)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.near_by_item, parent, false)
        return NearByViewHolder(view)
    }

    override fun onBindViewHolder(holder: NearByViewHolder, position: Int) {
        val userDtl = data.value!!.get(position)
        holder.bind(userDtl.imgs)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = data.value!!.size

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
}
