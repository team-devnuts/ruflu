package com.devnuts.ruflu.ui.onboarding.fragment.five

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingAcademyBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

/* 온보딩 - 5 : 학력 */
class OnboardingAcademyFragment : Fragment() {
    private var _binding: FragmentOnboardingAcademyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingAcademyViewModel by viewModels()

    private lateinit var academyLayoutManager: RecyclerView.LayoutManager

    private val academyAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.ACADEMY_CEL -> {
                        academyAdapter.submitList(viewModel.initAcademy(position))
                    }

                    else -> {}
                }
            }

            override fun onTouch(view: View, model: Model, event: MotionEvent) {
            }

            override fun onSwipe(position: Int, direction: Int) {}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingAcademyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeRecyclerView()
        initializeObserve()
    }

    private fun initializeObserve() {
        viewModel.academy.observe(viewLifecycleOwner) {
            if (viewModel.academy.value!! < 0) return@observe

            with(binding.btnAcademy) {
                isEnabled = true
                isSelected = true
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.btnAcademy.isEnabled = false
        academyAdapter.submitList(viewModel.initAcademy(-1))

        binding.rvAcademy.adapter = academyAdapter
        academyLayoutManager = GridLayoutManager(requireContext(), 2)
        academyLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = ((width - 50) / spanCount)
                return true
            }
        }

        binding.rvAcademy.setHasFixedSize(true)
        binding.rvAcademy.layoutManager = academyLayoutManager
    }

    private fun setupNavigation() {
        binding.btnAcademy.setOnClickListener {
            findNavController().navigate(R.id.action_academyFragment_to_jobFragment)
        }
    }
}
