package com.devnuts.ruflu.ui.onboarding.fragment.six

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingFancyBinding

/* 온보딩 - 7 : 스타일 */
class OnboardingFancyFragment : Fragment() {
    private var _binding: FragmentOnboardingFancyBinding? = null
    private val binding get() = _binding!!
    //private val viewModel: FormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingFancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {
        binding.formNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_fancyFragment_to_photoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
