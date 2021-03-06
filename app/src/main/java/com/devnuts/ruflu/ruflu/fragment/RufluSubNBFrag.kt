package com.devnuts.ruflu.ruflu.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ruflu.fragment.viewmodel.RufluSubNBViewModel

class RufluSubNBFrag : Fragment() {

    companion object {
        fun newInstance() = RufluSubNBFrag()
    }

    private lateinit var viewModel: RufluSubNBViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ruflu_sub_nb_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RufluSubNBViewModel::class.java)

    }

}