package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingJobBinding

/* 온보딩 - 9 : 직업 */
class OnboardingJobFragment : Fragment() {
    private var _binding: FragmentOnboardingJobBinding? = null
    private val binding get() = _binding!!
    //private val viewModel: OccupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {
        binding.occupNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_jobFragment_to_religionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
