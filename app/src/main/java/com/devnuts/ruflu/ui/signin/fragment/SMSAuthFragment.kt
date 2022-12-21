package com.devnuts.ruflu.ui.signin.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentSmsAuthBinding

class SMSAuthFragment : Fragment() {
    private var _binding: FragmentSmsAuthBinding? = null
    private val binding get() = _binding ?: error("View 참조 실패")
    private val viewModel: SMSViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.observer = viewModel.Observer()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // back()
        initializeViewBind()
        observeValue()
    }

    private fun observeValue() {
        viewModel.code.observe(viewLifecycleOwner) {
            binding.phoneAuthBtn.isSelected = it.length == 4
        }
    }
    // 인증번호가 다 적혀있는 지 확인 후, 다 존재한다면? pass !

    private fun back() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initializeViewBind() {
        with(viewModel) {
            verifyCodeEtList.add(binding.signCertNum1)
            verifyCodeEtList.add(binding.signCertNum2)
            verifyCodeEtList.add(binding.signCertNum3)
            verifyCodeEtList.add(binding.signCertNum4)

            Observer().onKeyListener(verifyCodeEtList[0])
            Observer().onKeyListener(verifyCodeEtList[1])
            Observer().onKeyListener(verifyCodeEtList[2])
            Observer().onKeyListener(verifyCodeEtList[3])
        }

        binding.phoneAuthBtn.setOnClickListener {
            findNavController().navigate(R.id.action_smsAuthFragment_to_registerEmailFragment)
        }
    }

    companion object {
        val FAG = "SMSAuthFragment"
    }
}
