package com.devnuts.ruflu.ui.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentRegPhotoUpldBinding
import com.devnuts.ruflu.ui.onboarding.viewmodel.PhotoUpldViewModel

/* 온보딩 - 12 : 사진 업로드 */
class PhotoUpldFragment : Fragment() {
    private var _binding: FragmentRegPhotoUpldBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: PhotoUpldViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegPhotoUpldBinding.inflate(inflater, container, false)
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
        curProgressBar.progress = 100
    }

    private fun initializeView() {
        binding.photoUpldNextBnt.setOnClickListener {
            findNavController().navigate(R.id.action_alcohFragment_to_loop)
        }
    }
}
