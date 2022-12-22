package com.devnuts.ruflu.ui.chat.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.ui.chat.viewmodel.ChatSubNBViewModel

class ChatSubNBFrag : Fragment() {
    private lateinit var viewModel: ChatSubNBViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_sub_nb_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatSubNBViewModel::class.java)
    }

    companion object {
        fun newInstance() = ChatSubNBFrag()
    }
}
