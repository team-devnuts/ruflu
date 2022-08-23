package com.devnuts.ruflu.mypage.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnuts.ruflu.R

class MypageFragment : Fragment() {

    companion object {
        fun newInstance() = MypageFragment()
    }

    private lateinit var viewModel: MypageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mypage_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MypageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}