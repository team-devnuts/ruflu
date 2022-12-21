package com.devnuts.ruflu.ui.like.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.like.viewmodel.RufluSubNBViewModel

class RufluSubNBFrag : Fragment() {

    companion object {
        fun newInstance() = RufluSubNBFrag()
    }

    private lateinit var viewModel: RufluSubNBViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ruflu_sub_nb_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RufluSubNBViewModel::class.java)
    }
}
