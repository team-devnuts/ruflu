package com.devnuts.ruflu.ui.onboarding.fragment.four

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingHeightBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.adapter.itemDecoration.VerticalItemDecorator
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

/* 온보딩 - 4 : 키  */
class OnboardingHeightFragment : Fragment() {
    private var _binding: FragmentOnboardingHeightBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingHeightViewModel by viewModels()

    private val heightAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.HEIGHT_CEL -> {
                        heightAdapter.submitList(viewModel.initHeight(position))
                    }

                    else -> {}
                }
            }

            override fun onTouch(view: View, model: Model, event: MotionEvent) {
                TODO("Not yet implemented")
            }

            override fun onSwipe(position: Int, direction: Int) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingHeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeRecyclerView()
        initializeObserve()
    }

    private fun initializeObserve() {
        viewModel.height.observe(viewLifecycleOwner) {
            if (viewModel.height.value!! < 0) return@observe

            with(binding.btnHeight) {
                isSelected = true
                isEnabled = true
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.btnHeight.isEnabled = false

        heightAdapter.submitList(viewModel.initHeight(-1))

        with(binding.rvHeight) {
            adapter = heightAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalItemDecorator(10, requireContext()))
        }
    }

    private fun setupNavigation() {
        binding.btnHeight.setOnClickListener {
            findNavController().navigate(R.id.action_heightFragment_to_academyFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
