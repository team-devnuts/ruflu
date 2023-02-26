package com.devnuts.ruflu.ui.signin.fragment

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentLoginBinding
import com.devnuts.ruflu.ui.signin.viewmodel.LoginViewModel
import com.devnuts.ruflu.util.HashKey
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        getHash()
        kakaologin()
        intentActivity()
    }

    private fun intentActivity() {
        viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
            if (isNew) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun kakaologin() {
        binding.btnKakaoLogin.setOnClickListener {
            viewModel.checkExistenceToken(requireContext())
        }
    }

    private fun phoneLogin() {
        binding.btnPhoneLogin.setOnClickListener {
            // SMS_API 로 넘겨주기
        }
    }

    private fun initializeView() {
        binding.tvLoginRestore.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_accountRestoreFragment)
        }

        binding.btnPhoneLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_smsFragment)
        }

        val transform = Linkify.TransformFilter(object : Linkify.TransformFilter, (Matcher, String) -> String {
            override fun transformUrl(match: Matcher?, url: String?): String { return "" }
            override fun invoke(p1: Matcher, p2: String): String { return "" }
        })

        val privacyPolicy = Pattern.compile("개인정보 취급 방침")
        val termsAgreement = Pattern.compile("이용 약관")

        Linkify.addLinks(
            binding.tvPrivateOne,
            privacyPolicy,
            "https://devnuts.notion.site/47c9867484694a4f8a2dcf18f48bd0e9",
            null,
            transform)
        Linkify.addLinks(
            binding.tvPrivateOne,
            termsAgreement,
            "https://devnuts.notion.site/b400e13695f949f8b9233fd1c4f997b9",
            null,
            transform
        )
    }


    private fun getHash() {
        val hashKey = HashKey()
        hashKey.getHashKey(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
