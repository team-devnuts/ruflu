package com.devnuts.ruflu.ui.onboarding.fragment.six

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingJobBinding

/* 온보딩 - 6 : 직업 */
class OnboardingJobFragment : Fragment() {
    private var _binding: FragmentOnboardingJobBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingJobViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        initializeListener()
        initializeObserve()

    }


    private fun initializeObserve() {
        with(viewModel) {
            job.observe(viewLifecycleOwner) {
                binding.btnJob.isEnabled = !job.value.isNullOrBlank()
                binding.btnJob.isSelected = !job.value.isNullOrBlank()
            }
        }
    }

    private fun initializeListener() {
        binding.etJob.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.changeEditText(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun setupNavigation() {
        binding.btnJob.setOnClickListener {
            findNavController().navigate(R.id.action_jobFragment_to_fancyFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
