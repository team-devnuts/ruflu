package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingNickNameBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.NickNameViewModel

/* 온보딩 - 4 : 닉네임 */
class OnboardingNickNameFragment : Fragment() {
    private var _binding: FragmentOnboardingNickNameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NickNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingNickNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar()
        initializeView()
    }

    private fun progressBar() {
        val curProgressBar = binding.pbLoading
        curProgressBar.progress = 32
    }

    private fun initializeView() {
        binding.nickNameNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_nickNameFragment_to_locationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
