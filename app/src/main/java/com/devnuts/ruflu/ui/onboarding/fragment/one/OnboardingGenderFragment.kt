package com.devnuts.ruflu.ui.onboarding.fragment.one

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.R
import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.databinding.FragmentOnboardingGenderBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.onboarding.GenderUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

/* 온보딩 - 1 : 성별  */
class OnboardingGenderFragment : Fragment() {
    private var _binding: FragmentOnboardingGenderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingGenderViewModel by viewModels()
    private var flag: Boolean = false
    private lateinit var genderLayoutManager: RecyclerView.LayoutManager

    private val genderAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.GENDER_CEL -> {
                        flag = true
                        val rv = binding.rvGender
                        viewModel.genderSingleChoice(rv, position)
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
        _binding = FragmentOnboardingGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeRecyclerView()
        initializeObserve()
    }

    private fun initializeRecyclerView() {
        genderAdapter.submitList(
            listOf(
                GenderUIModel(resourceId = R.drawable.onboarding_female_selector, name = "여성"),
                GenderUIModel(resourceId = R.drawable.onboarding_male_selector, name = "남성")
            )
        )

        binding.rvGender.adapter = genderAdapter
        genderLayoutManager = GridLayoutManager(requireContext(), 2)
        genderLayoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = ((width - 50) / spanCount)
                return true
            }
        }

        binding.rvGender.setHasFixedSize(true)
        binding.rvGender.layoutManager = genderLayoutManager
    }

    private fun initializeObserve() {
        viewModel.gender.observe(viewLifecycleOwner) {
            if (viewModel.gender.value!! < 0) return@observe

            with(binding.btnGender) {
                isSelected = true
                isEnabled = true
            }
        }
    }

    private fun setupNavigation() {
        binding.btnGender.setOnClickListener {
            viewModel.gender.value?.let { value ->
                RufluApp.sharedPreference.putSettingInt(
                    "gender",
                    value
                )
            }
            findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = "Lifecycle"
    }
}
