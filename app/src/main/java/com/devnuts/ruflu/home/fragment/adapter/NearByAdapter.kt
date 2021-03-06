package com.devnuts.ruflu.home.fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtill
import com.devnuts.ruflu.home.model.UserDtl

class NearByAdapter(val data: MutableLiveData<ArrayList<UserDtl>>) : RecyclerView.Adapter<NearByAdapter.NearByViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var view : View
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }


    inner class NearByViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind (imgs: List<String>) {
            val imgView = view.findViewById<ImageView>(R.id.nbUPfImg)
            Log.d("is exist img??", "${imgs.isNotEmpty()}")
            if(imgs.isNotEmpty())
                UserUtill.setImageWithGlide(view, imgs[0], imgView)
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
