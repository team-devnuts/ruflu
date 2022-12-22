package com.devnuts.ruflu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemLikeLv1UserBinding
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.ui.like.listener.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.model.home.UserDtl
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber

class SomeAdapter(
    private val likeLv1Users: ArrayList<UserDtl>
) : RecyclerView.Adapter<SomeAdapter.LikeLv1ViewHolder>(),
    SomeTouchHelperCallback.OnItemMoveListener {

    private lateinit var view: View
    private lateinit var binding: ItemLikeLv1UserBinding
    private lateinit var userImgView: CircleImageView
    private lateinit var seLikeLv1OnClickListener: OnItemClickListener
    private lateinit var seLikeLv1OnSwipeListener: OnItemSwipeListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    interface OnItemSwipeListener {
        fun onSwipe(user: UserDtl, direction: Int)
    }

    inner class LikeLv1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(likeLv1User: UserDtl) {

            if (likeLv1User.imgs.isNotEmpty())
                UserUtil.setImageWithGlide(itemView, likeLv1User.imgs.get(0), userImgView)
            else userImgView.setImageResource(R.drawable.noimg_fac)

            binding.seLv1NickNm.text = likeLv1User.nick_nm
            binding.seLv1Age.text = "${UserUtil.getAge(likeLv1User.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeLv1ViewHolder {
        binding = ItemLikeLv1UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view = binding.root
        userImgView = binding.seLikeLv1UserImg

        return LikeLv1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeLv1ViewHolder, position: Int) {
        userImgView.setOnClickListener {
            seLikeLv1OnClickListener.onClick(it, position)
        }
        holder.bind(likeLv1Users[position])
    }

    override fun getItemCount(): Int {
        return likeLv1Users.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemsWipe(position: Int, direction: Int) {
        Timber.tag("swipe").d("direction : $direction")
        val lv1User = likeLv1Users[position]

        seLikeLv1OnSwipeListener.onSwipe(lv1User, direction)
        likeLv1Users.remove(lv1User)
        notifyItemRemoved(position)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.seLikeLv1OnClickListener = onItemClickListener
    }

    fun setItemSwipeListener(onItemSwipeListener: OnItemSwipeListener) {
        this.seLikeLv1OnSwipeListener = onItemSwipeListener
    }
}
