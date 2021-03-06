package com.devnuts.ruflu.mypage.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnuts.ruflu.R

class MypageSubFrag : Fragment() {

    companion object {
        fun newInstance() = MypageSubFrag()
    }

    private lateinit var viewModel: MypageSubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mypage_sub_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MypageSubViewModel::class.java)
        // TODO: Use the ViewModel
    }

}