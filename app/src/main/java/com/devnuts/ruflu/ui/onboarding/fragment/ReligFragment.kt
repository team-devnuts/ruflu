package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegReligBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.ReligViewModel

/* 온보딩 - 10 : 종교 */
class ReligFragment : Fragment() {
    private var _binding: FragmentRegReligBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: ReligViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegReligBinding.inflate(inflater, container, false)
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
        curProgressBar.progress = 82
    }

    private fun initializeView() {
        binding.religNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_religFragment_to_alcohFragment)
        }
    }
}
