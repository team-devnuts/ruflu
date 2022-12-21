package com.devnuts.ruflu.ui.signin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentAccountRestoreBinding

class AccountRestoreFragment : Fragment() {
    private var _binding: FragmentAccountRestoreBinding? = null
    private val binding get() = _binding ?: error("View 참조 초기화 실패")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountRestoreBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back()
        initializedView()
    }

    private fun back() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initializedView() {
        binding.accountRestoreBnt.setOnClickListener {
            findNavController().navigate(R.id.action_accountRestoreFragment_to_accountRestoreAuthFragment)
        }
    }
}
