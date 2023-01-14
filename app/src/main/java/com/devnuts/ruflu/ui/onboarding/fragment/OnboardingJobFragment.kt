package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegOccupBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.OccupViewModel

/* 온보딩 - 9 : 직업 */
class OccupFragment : Fragment() {
    private var _binding: FragmentRegOccupBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: OccupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegOccupBinding.inflate(inflater, container, false)
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
        curProgressBar.progress = 74
    }

    private fun initializeView() {
        binding.occupNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_occupFragment_to_religFragment)
        }
    }
}
