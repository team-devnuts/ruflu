package com.devnuts.ruflu.register.flow1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.TermsOfUseFragmentBinding

class TermsOfUseFragment : Fragment() {
    private var _binding : TermsOfUseFragmentBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel : TermsOfUseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TermsOfUseFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar();
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 8;
    }

    private fun initializeView() {
        binding.termsOfUseNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_termsOfUseFragment_to_genderFragment)
        }
    }
}