package com.devnuts.ruflu.ui.onboarding.fragment.eight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingPhotoBinding

/* 온보딩 - 12 : 사진 업로드 */
class OnboardingPhotoFragment : Fragment() {
    private var _binding: FragmentOnboardingPhotoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingPhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {
        binding.photoUpldNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_photoFragment_to_loop)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
