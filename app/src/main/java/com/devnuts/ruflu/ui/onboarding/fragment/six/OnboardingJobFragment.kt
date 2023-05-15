package com.devnuts.ruflu.ui.onboarding.fragment.six

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.R
import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.databinding.FragmentOnboardingJobBinding
import java.util.regex.Pattern

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

        initView()
        setupNavigation()
        initializeListener()
        initializeObserve()

    }

    /* 영어 & 한글만 가능 */
    private fun initView() {
        /** 문자열필터(EditText Filter) */
        var filterAlphaNumSpace = InputFilter { source, start, end, dest, dstart, dend ->
            /*
                [요약 설명]
                1. 정규식 패턴 ^[a-z] : 영어 소문자 허용
                2. 정규식 패턴 ^[A-Z] : 영어 대문자 허용
                3. 정규식 패턴 ^[ㄱ-ㅣ가-힣] : 한글 허용
                4. 정규식 패턴 ^[0-9] : 숫자 허용
                5. 정규식 패턴 ^[ ] or ^[\\s] : 공백 허용
            */
            val ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z\\s]+$")
            if (!ps.matcher(source).matches()) {
                ""
            } else source
        }

        binding.etJob.filters = arrayOf(filterAlphaNumSpace)
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
            RufluApp.sharedPreference.putSettingString("job", "${viewModel.job.value}")
            findNavController().navigate(R.id.action_jobFragment_to_fancyFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
