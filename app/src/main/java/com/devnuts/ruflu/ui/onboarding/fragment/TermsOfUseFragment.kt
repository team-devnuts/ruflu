package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegTermsOfUseBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.TermsOfUseViewModel

/* 온보딩 - 1 : 회원가입 동의 */
class TermsOfUseFragment : Fragment() {
    private var _binding: FragmentRegTermsOfUseBinding? = null
    private val binding get() = _binding ?: error("View 참조 초기화 실패")
    private val viewModel: TermsOfUseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegTermsOfUseBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 8
    }

    private fun initializeView() {
        binding.termsOfUseNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_termsOfUseFragment_to_genderFragment)
        }
    }
}
