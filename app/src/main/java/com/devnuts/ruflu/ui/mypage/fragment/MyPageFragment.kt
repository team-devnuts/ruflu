package com.devnuts.ruflu.ui.mypage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.mypage.viewmodel.MypageViewModel

class MyPageFragment : Fragment() {

    companion object {
        fun newInstance() = MyPageFragment()
    }

    private lateinit var viewModel: MypageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MypageViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
