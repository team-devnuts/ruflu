package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingAcademyBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.EduViewModel

/* 온보딩 - 8 : 학력 */
class OnboardingAcademyFragment : Fragment() {
    private var _binding: FragmentOnboardingAcademyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EduViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingAcademyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 66
    }

    private fun initializeView() {
        binding.eduNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_eduFragment_to_occupFragment)
        }
    }
}
