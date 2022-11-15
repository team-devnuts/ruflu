package com.devnuts.ruflu.login.loginAPI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.FragmentLoginBinding
import com.devnuts.ruflu.login.activity.RegisterActivity
import com.devnuts.ruflu.util.HashKey

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null // UI와 binding
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: LoginViewModel by viewModels() // 위임초기화

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(LOGINFRAGMENT, "onCreateView")
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        // LiveData 를 DataBinding 으로 쓸 경우, 꼭 써줘야한다. Observable 대신 LiveData 를 사용하여 DataBinding 이 가능
        // DataBinding 을 이용해 View 에 LiveData 를 Binding 시키면 LiveData 의 값이 변경될 때 View 의 Data 가 자동으로 바뀌기 때문에 소스코드를 이용한 Data Setting 같은 코드를 줄일 수 있습니다.
        binding.lifecycleOwner = viewLifecycleOwner // layout 의 viewModel 변수를 viewModel 과 바인딩 해줌.

        // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의 스턴스를 활용하여 생성된 뷰를 프래그먼트에 표시 합니다

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOGINFRAGMENT, "onViewCreated")

        initializeView()

        getHash()
        kakaologin()
        intentActivity()
    }



    // LiveData 를 이용하여, Activity 화면 전환
    private fun intentActivity() {
        // isNew 로 넘어오는 param 값은 LiveData 의 value
        viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
            if(isNew) { // 새로운 아이디일 경우, register 로 넘기기
                val intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else { // 존재하는 아이디일 경우, MainActivity 로
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
    }
    private fun getHash() {
        val hashKey = HashKey() // 인스턴스 생성
        hashKey.getHashKey(requireContext()) // fragment 에서는 getContext 가 아닌, requireContext 를사용
    }

    companion object {
        val LOGINFRAGMENT = "LoginViewModel"
    }
}