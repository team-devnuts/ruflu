package com.devnuts.ruflu.login.loginAPI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devnuts.ruflu.MainActivity
import com.devnuts.ruflu.databinding.FragmentLoginBinding
import com.devnuts.ruflu.login.activity.RegisterActivity
import com.devnuts.ruflu.util.HashKey

class LoginFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentLoginBinding? = null // UI와 binding
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 x")
    private val viewModel: LoginViewModel by viewModels() // 위임초기화?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        // LiveData를 Databinding으로 쓸 경우, 꼭 써줘야한다. Observable 대신 LiveData를 사용하여 DataBinding 이 가능
        // DataBinding을 이용해 View에 LiveData를 Binding 시키면 LiveData의 값이 변경될 때 View의 Data가 자동으로 바뀌기 때문에 소스코드를 이용한 Data Setting 같은 코드를 줄일 수 있습니다.
        binding.lifecycleOwner = viewLifecycleOwner // layout의 viewModel 변수를 viewModel과 바인딩 해줌.

        // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의 스턴스를 활용하여 생성된 뷰를 프래그먼트에 표시 합니다
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHash()
        kakaologin()
        intentActivity()
    }
    // LiveData를 이용하여, Activity 화면 전환
    private fun intentActivity() {
        // isNew 로 넘어오는 param 값은 LiveData의 value
        viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
            if(isNew) {
                Log.d("true : ", "값" + isNew);
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else {
                Log.d("true : ", "값" + isNew);
                val intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun kakaologin() {
        binding.btnKakaoLogin.setOnClickListener {
            viewModel.newKakao(requireContext())
        }
    }

    private fun getHash() {
        val hashKey = HashKey() // 인스턴스 생성
        hashKey.getHashKey(requireContext()) // fragment 에서는 getContext 가 아닌, requireContext 를사용
    }
}