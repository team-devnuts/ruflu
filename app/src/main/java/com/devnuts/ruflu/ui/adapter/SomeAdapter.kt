package com.devnuts.ruflu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemSomeUserBinding
import com.devnuts.ruflu.ui.like.listener.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
import com.devnuts.ruflu.util.UserUtil
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber

class SomeAdapter(
    private val someUsers: ArrayList<UserDetailUIModel>
) : RecyclerView.Adapter<SomeAdapter.SomeViewHolder>(),
    SomeTouchHelperCallback.OnItemMoveListener {

    private lateinit var view: View
    private lateinit var binding: ItemSomeUserBinding
    private lateinit var userImagesView: CircleImageView
    private lateinit var someClickListener: OnItemClickListener
    private lateinit var someSwipeListener: OnItemSwipeListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    interface OnItemSwipeListener {
        fun onSwipe(user: UserDetailUIModel, direction: Int)
    }

    inner class SomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(someUser: UserDetailUIModel) {

            if (someUser.imgs.isNotEmpty())
                UserUtil.setImageWithGlide(itemView, someUser.imgs[0], userImagesView)
            else userImagesView.setImageResource(R.drawable.noimg_fac)

            binding.nickName.text = someUser.nick_nm
            binding.age.text = "${UserUtil.getAge(someUser.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SomeViewHolder {
        binding = ItemSomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view = binding.root
        userImagesView = binding.seLikeLv1UserImg

        return SomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SomeViewHolder, position: Int) {
        userImagesView.setOnClickListener {
            someClickListener.onClick(it, position)
        }
        holder.bind(someUsers[position])
    }

    override fun getItemCount(): Int {
        return someUsers.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemsWipe(position: Int, direction: Int) {
        Timber.tag("swipe").d("direction : $direction")
        val user = someUsers[position]

        someSwipeListener.onSwipe(user, direction)
        someUsers.remove(user)
        notifyItemRemoved(position)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.someClickListener = onItemClickListener
    }

    fun setItemSwipeListener(onItemSwipeListener: OnItemSwipeListener) {
        this.someSwipeListener = onItemSwipeListener
    }
}
