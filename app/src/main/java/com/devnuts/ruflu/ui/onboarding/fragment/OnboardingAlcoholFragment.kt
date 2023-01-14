package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegAlcohBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.AlcohViewModel

/* 온보딩 - 11 : 주량 정도 */
class AlcohFragment : Fragment() {
    private var _binding: FragmentRegAlcohBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: AlcohViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegAlcohBinding.inflate(inflater, container, false)
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
        curProgressBar.progress = 91
    }

    private fun initializeView() {
        binding.alcohNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_alcohFragment_to_photoUpldFragment)
        }
    }
}
