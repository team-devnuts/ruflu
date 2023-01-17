package com.devnuts.ruflu.ui.onboarding.fragment.three

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentOnboardingNickNameBinding


/* 온보딩 - 4 : 닉네임 */
class OnboardingNickNameFragment : Fragment() {
    private var _binding: FragmentOnboardingNickNameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OnboardingNickNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_onboarding_nick_name,
            container,
            false
        )
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
            nickName.observe(viewLifecycleOwner) {
                binding.btnNickName.isEnabled = !nickName.value.isNullOrBlank()
                binding.btnNickName.isSelected = !nickName.value.isNullOrBlank()
            }
        }
    }

    private fun initializeListener() {
        binding.etNickName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.changeEditText(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun setupNavigation() {
        binding.btnNickName.setOnClickListener {
            findNavController().navigate(R.id.action_nickNameFragment_to_heightFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
