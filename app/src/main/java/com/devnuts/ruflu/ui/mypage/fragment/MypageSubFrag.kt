package com.devnuts.ruflu.ui.mypage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.mypage.viewmodel.MypageSubViewModel

class MypageSubFrag : Fragment() {

    companion object {
        fun newInstance() = MypageSubFrag()
    }

    private lateinit var viewModel: MypageSubViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
