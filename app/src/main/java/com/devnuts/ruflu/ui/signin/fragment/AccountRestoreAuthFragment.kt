package com.devnuts.ruflu.ui.signin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devnuts.ruflu.databinding.FragmentAccountRestoreAuthBinding
import com.devnuts.ruflu.ui.signin.viewmodel.AccountRestoreViewModel

class AccountRestoreAuthFragment : Fragment() {
    private var _binding: FragmentAccountRestoreAuthBinding? = null
    private val binding get() = _binding ?: error("View 참조 초기화 실패")
    private val viewModel: AccountRestoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃이 참조하는 변수는 자동으로 대입되지 않기 때문에 뷰 측 onCreate(onCreateView) 로 돌아와 연결시켜야한다
        _binding = FragmentAccountRestoreAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back()
        observeValue()
    }

    // 좀 더 클린하게 코드 수정이 필요
    private fun observeValue() {
        binding.etEmail.addTextChangedListener {
            viewModel.email.observe(viewLifecycleOwner) {
                val email: String = binding.etEmail.text.toString()
                val checkValidation: Boolean = viewModel.validateEmail(email)

                if (email.isBlank()) binding.tvValidateText.isVisible = false
                else {
                    if (checkValidation) {
                        binding.accountRestoreAuthBnt.isSelected = true
                        binding.tvValidateText.isVisible = false
                    } else {
                        binding.accountRestoreAuthBnt.isSelected = false
                        binding.tvValidateText.isVisible = true
                    }
                }
            }
        }
    }

    private fun back() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
