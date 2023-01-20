package com.devnuts.ruflu.ui.signin.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentSmsBinding
import com.devnuts.ruflu.ui.signin.viewmodel.SMSViewModel

class SMSFragment : Fragment() {
    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SMSViewModel by viewModels()

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back()
        focusEditTextSetting()
        initViewSetting()
        observeValue()
    }

    /* observing */
    private fun observeValue() {
        binding.etPhoneNumber.addTextChangedListener {
            val phoneNumber: String = binding.etPhoneNumber.text.toString()
            val checkValidation: Boolean = viewModel.validatePhoneNumber(phoneNumber)
            binding.phoneAuthBtn.isSelected = checkValidation
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViewSetting() {
        /* recycler View 로 확장 해야할 부분 */
        binding.etCountry.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                Toast.makeText(context, "나라 클릭", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        binding.phoneAuthBtn.setOnClickListener {
            findNavController().navigate(R.id.action_smsFragment_to_smsAuthFragment)
        }
    }

    /* 포커스주는 방법 따로 공부해야 함. */
    private fun focusEditTextSetting() {
        val mInputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mInputMethodManager.showSoftInput(binding.etPhoneNumber, 0)
    }

    private fun back() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
