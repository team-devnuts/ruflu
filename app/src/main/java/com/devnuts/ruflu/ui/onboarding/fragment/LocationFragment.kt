package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegLocationBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.LocationViewModel

/* 온보딩 - 5 : 위치설정 */
class LocationFragment : Fragment() {
    private var _binding: FragmentRegLocationBinding? = null
    private val binding get() = _binding ?: error("View error")
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegLocationBinding.inflate(inflater, container, false)
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
        curProgressBar.progress = 41
    }

    private fun initializeView() {
        binding.locNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_locationFragment_to_heightFragment)
        }
    }
}
