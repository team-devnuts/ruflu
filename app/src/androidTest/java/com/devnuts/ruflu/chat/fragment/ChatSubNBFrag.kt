package com.devnuts.ruflu.chat.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnuts.ruflu.R
import com.devnuts.ruflu.chat.fragment.viewmodel.ChatSubNBViewModel

class ChatSubNBFrag : Fragment() {

    companion object {
        fun newInstance() = ChatSubNBFrag()
    }

    private lateinit var viewModel: ChatSubNBViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_sub_nb_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatSubNBViewModel::class.java)
        // TODO: Use the ViewModel
    }

}