package com.devnuts.ruflu.ruflu.fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.utill.UserUtill
import com.devnuts.ruflu.databinding.LikeLv1UserBinding
import com.devnuts.ruflu.home.model.UserDtl
import com.devnuts.ruflu.ruflu.fragment.RufluTouchHelperCallback
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv1User
import de.hdodenhof.circleimageview.CircleImageView

class RufluLikeLv1Adapter (
        private val likeLv1Users : ArrayList<UserDtl>
        ) : RecyclerView.Adapter<RufluLikeLv1Adapter.RufluLikeLv1ViewHolder>(), RufluTouchHelperCallback.OnItemMoveListener {

    private lateinit var view : View
    private lateinit var binding : LikeLv1UserBinding
    private lateinit var userImgView : CircleImageView
    private lateinit var seLikeLv1OnClickListener: OnItemClickListener
    private lateinit var seLikeLv1OnSwipeListener: OnItemSwipeListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    interface OnItemSwipeListener {
        fun onSwipe(user:UserDtl, direction: Int)
    }

    inner class RufluLikeLv1ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(likeLv1User: UserDtl) {

            if(likeLv1User.imgs.isNotEmpty() )
                UserUtill.setImageWithGlide(itemView,likeLv1User.imgs.get(0), userImgView)
            else userImgView.setImageResource(R.drawable.noimg_fac)


            binding.seLv1NickNm.text = likeLv1User.nick_nm
            binding.seLv1Age.text = "${UserUtill.getAge(likeLv1User.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RufluLikeLv1ViewHolder {
        binding = LikeLv1UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view = binding.root
        userImgView = binding.seLikeLv1UserImg

        return RufluLikeLv1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RufluLikeLv1ViewHolder, position: Int) {
        userImgView.setOnClickListener {
            seLikeLv1OnClickListener.onClick(it, position)
        }
        holder.bind(likeLv1Users.get(position))
    }

    override fun getItemCount(): Int {
        return likeLv1Users.size
    }

    override fun onItemMove(fromposition: Int, toPosition: Int) {

    }

    override fun onItemsWipe(position: Int, direction: Int) {
        Log.d("swipe", "direction : ${direction}")
        val lv1User = likeLv1Users.get(position)

        seLikeLv1OnSwipeListener.onSwipe(lv1User, direction)
        likeLv1Users.remove(lv1User)
        notifyItemRemoved(position)
    }

    fun setItemClickListener(onItemClickListener: RufluLikeLv1Adapter.OnItemClickListener) {
        this.seLikeLv1OnClickListener = onItemClickListener
    }

    fun setItemSwipeListener(onItemSwipeListener: RufluLikeLv1Adapter.OnItemSwipeListener) {
        this.seLikeLv1OnSwipeListener = onItemSwipeListener
    }



}