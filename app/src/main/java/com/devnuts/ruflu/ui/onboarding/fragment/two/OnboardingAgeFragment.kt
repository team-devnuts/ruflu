package com.devnuts.ruflu.ui.onboarding.fragment.two

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
import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.databinding.FragmentOnboardingAgeBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.adapter.itemDecoration.VerticalItemDecorator
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

/* 온보딩 - 2 : 나이 */
class OnboardingAgeFragment : Fragment() {
    private var _binding: FragmentOnboardingAgeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingAgeViewModel by viewModels()

    private val ageAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.AGE_CEL -> {
                        ageAdapter.submitList(viewModel.initAge(position))
                    }

                    else -> {}
                }
            }

            override fun onTouch(view: View, model: Model, event: MotionEvent) {}

            override fun onSwipe(position: Int, direction: Int) {}

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingAgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeRecyclerView()
        initializeObserve()
    }

    private fun initializeObserve() {
        viewModel.age.observe(viewLifecycleOwner) {
            if (viewModel.age.value!! < 0) return@observe

            with(binding.btnAge) {
                isSelected = true
                isEnabled = true
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.btnAge.isEnabled = false

        ageAdapter.submitList(viewModel.initAge(-1))

        with(binding.rvAge) {
            adapter = ageAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalItemDecorator(10, requireContext()))
        }
    }


    private fun setupNavigation() {
        binding.btnAge.setOnClickListener {
            RufluApp.sharedPreference.putSettingString("age", "${viewModel.age.value?.plus(20)}")
            findNavController().navigate(R.id.action_ageFragment_to_nickNameFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
