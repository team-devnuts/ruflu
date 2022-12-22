package com.devnuts.ruflu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.databinding.LikeLv2UserBinding
import com.devnuts.ruflu.ui.model.home.UserDtl
import de.hdodenhof.circleimageview.CircleImageView

class LikeLv2Adapter(
    private val likeLv2Users: ArrayList<UserDtl>
) : RecyclerView.Adapter<LikeLv2Adapter.LikeLv2ViewHolder>() {

    private lateinit var view: View
    private lateinit var binding: LikeLv2UserBinding
    private lateinit var userImgView: CircleImageView
    private lateinit var seLikeLv2UserClickListener: OnItemClickListener
    private lateinit var seLikeLv2TalkClickListener: OnItemClickListener
    private lateinit var seLikeLv2CancelClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    inner class LikeLv2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(likeLv2User: UserDtl) {
            if ("" == likeLv2User.imgs[0])
                UserUtil.setImageBitmap(likeLv2User.imgs.get(0), userImgView)
            else userImgView.setImageResource(R.drawable.noimg_fac)
            binding.seLv2NickNm.text = likeLv2User.nick_nm
            binding.seLv2Age.text = "${UserUtil.getAge(likeLv2User.birth)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeLv2ViewHolder {
        binding = LikeLv2UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view = binding.root
        userImgView = binding.seLikeLv2UserImg

        return LikeLv2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeLv2ViewHolder, position: Int) {
        initListener(position)
        holder.bind(likeLv2Users[position])
    }

    override fun getItemCount(): Int {
        return likeLv2Users.size
    }

    fun setUserClickListener(onItemClickListener: OnItemClickListener) {
        this.seLikeLv2UserClickListener = onItemClickListener
    }

    fun setTalkClickListener(onItemClickListener: OnItemClickListener) {
        this.seLikeLv2TalkClickListener = onItemClickListener
    }

    fun setCancelClickListener(onItemClickListener: OnItemClickListener) {
        this.seLikeLv2CancelClickListener = onItemClickListener
    }

    private fun initListener(position: Int) {
        userImgView.setOnClickListener {
            seLikeLv2UserClickListener.onClick(it, position)
        }

        binding.seLv2CancelBtn.setOnClickListener {
            seLikeLv2TalkClickListener.onClick(it, position)
        }

        binding.seLv2TalkBtn.setOnClickListener {
            seLikeLv2CancelClickListener.onClick(it, position)
        }
    }
}
