package com.devnuts.ruflu.register.flow2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.GenderFragmentBinding

@Suppress("UseExpressionBody")
class GenderFragment : Fragment() {
    //UI binding
    private var _binding : GenderFragmentBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel : GenderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GenderFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 16;
    }

    private fun initializeView() {
        binding.genderNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_birthFragment)
        }
    }
}
