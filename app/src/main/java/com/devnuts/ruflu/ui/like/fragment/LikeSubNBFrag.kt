package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.like.viewmodel.LikeSubNBViewModel

class LikeSubNBFrag : Fragment() {
    private lateinit var viewModel: LikeSubNBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LikeSubNBViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ruflu_sub_nb_fragment, container, false)
    }

    companion object {
        fun newInstance() = LikeSubNBFrag()
    }
}
