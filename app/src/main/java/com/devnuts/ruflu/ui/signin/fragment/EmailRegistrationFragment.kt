package com.devnuts.ruflu.ui.signin.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devnuts.ruflu.databinding.FragmentEmailRegistrationBinding
import com.devnuts.ruflu.login.activity.RegisterActivity
import com.devnuts.ruflu.ui.signin.viewmodel.EmailRegistrationViewModel

class EmailRegistrationFragment : Fragment() {

    private var _binding : FragmentEmailRegistrationBinding? = null
    private val binding get() = _binding?: error("View 참조 초기화 실패")
    private val viewModel : EmailRegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmailRegistrationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back()
        initializedView()
    }

    private fun initializedView() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.emailRegisBnt.isSelected = viewModel.validateEmail(s.toString())
                /**
                 * TODO : Retrofit 로직처리 하기
                 */

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.emailRegisBnt.setOnClickListener {
            intentActivity()
        }
    }

    private fun intentActivity() {
        val intent = Intent(activity, RegisterActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun back() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}