package com.devnuts.ruflu.ui.onboarding.fragment.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingGenderBinding

/* 온보딩 - 1 : 성별  */
class OnboardingGenderFragment : Fragment() {
    private var _binding: FragmentOnboardingGenderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingGenderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 16
    }

    private fun initializeView() {
        binding.genderNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_genderFragment_to_birthFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
