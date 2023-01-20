package com.devnuts.ruflu.ui.onboarding.fragment.seven

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingFancyBinding
import com.devnuts.ruflu.ui.adapter.ModelRecyclerViewAdapter
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

/* 온보딩 - 7 : 이상형 */
class OnboardingFancyFragment : Fragment() {
    private var _binding: FragmentOnboardingFancyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingFancyViewModel by viewModels()

    private lateinit var fancyLayoutManager: RecyclerView.LayoutManager

    private val fancyAdapter: ModelRecyclerViewAdapter<Model> by lazy {
        ModelRecyclerViewAdapter(object : ModelAdapterListener{
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.FANCY_CEL -> {
                        fancyAdapter.submitList(viewModel.initFancy(position))
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
        _binding = FragmentOnboardingFancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeRecyclerView()
        initializeObserve()
    }

    private fun initializeObserve() {
        viewModel.fancy.observe(viewLifecycleOwner) {
            if (viewModel.fancy.value!! < 0) return@observe

            with(binding.btnFancy) {
                isEnabled = true
                isSelected = true
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.btnFancy.isEnabled = false
        fancyAdapter.submitList(viewModel.initFancy(-1))

        binding.rvFancy.adapter = fancyAdapter
        //fancyLayoutManager = GridLayoutManager(requireContext(), 2)
        fancyLayoutManager = object : GridLayoutManager(requireContext(), 3) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = ((width - 50) / spanCount)
                return true
            }
        }
        binding.rvFancy.layoutManager = fancyLayoutManager
    }

    private fun setupNavigation() {
        binding.btnFancy.setOnClickListener {
            findNavController().navigate(R.id.action_fancyFragment_to_photoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
