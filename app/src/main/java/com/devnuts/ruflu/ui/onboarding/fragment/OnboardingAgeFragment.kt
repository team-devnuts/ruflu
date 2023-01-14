package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingAgeBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.BirthViewModel

/* 온보딩 - 3 : 생년원일 */
class OnboardingAgeFragment : Fragment() {
    private var _binding: FragmentOnboardingAgeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BirthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingAgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 24
    }

    private fun initializeView() {
        binding.birthNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_birthFragment_to_nickNameFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
