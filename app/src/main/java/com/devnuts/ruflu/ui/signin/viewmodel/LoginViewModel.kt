package com.devnuts.ruflu.ui.signin.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.data.api.UserService
import com.devnuts.ruflu.data.api.request.signin.RequestLoginData
import com.devnuts.ruflu.data.api.response.signin.ResponseLoginData
import com.devnuts.ruflu.ui.model.signin.KakaoUser
import com.devnuts.ruflu.util.SharedPreferenceToken
import com.kakao.sdk.auth.*
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var kakaoUser = KakaoUser("", "")
    val isNew = MutableLiveData<Boolean>()

    fun checkExistenceToken(context: Context) {
        // 단, hasToken()의 결과가 true 라도 현재 사용자가 로그인 상태임을 보장하지 않습니다.
        if (AuthApiClient.instance.hasToken()) { // 앱 실행 시 사용자가 앞서 로그인을 통해 발급 받은 토큰이 있는지 확인
            // 서버에 액세스 토큰의 유효성을 확인할 수 있는 API
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                // 현재 유효한 access 토큰이 없음
                // access 토큰이 만료된 것이라면 sdk 내부에서 accessToken 을 갱신한다.
                if (error != null) {
                    if (error is KakaoSdkError) {
                        // access 토큰 갱신까지 실패한 것이기 때문에 refresh 토큰이 유효하지 않음, 로그인 필요
                        Timber.e("로그인 필요 / $error.toString()")
                        loginKakaoUser(context)
                    } else {
                        // 기타 에러 (에러 시, 레퍼런스 참고해야할 것)
                        Timber.e("기타 에러 / $error.toString()")
                    }
                } else {
                    // 토큰 유효성 체크 성공(필요 시 sdk 내부에서 토큰 갱신됨)
                    Timber.i("로그인 성공 (필요 시 토큰 갱신) / $tokenInfo.toString()")
                    // 토큰 갱신 API 호출 함수 구현 (USER_TOKEN 최신화 작업이 필요)
                    loginKakaoUser(context)
                }
            }
        } else {
            // hasToken() API 의 결과가 false 라면 토큰이 없는 상태이므로 사용자가 로그인할 수 있도록 처리
            Timber.i("로그인 필요")
            loginKakaoUser(context)
        }
    }

    private fun loginKakaoUser(context: Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(context, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(context, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
                            .show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(context, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(context, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        if (error.toString() == ClientErrorCause.TokenNotFound.toString()) {
                            Toast.makeText(context, "기타 에러", Toast.LENGTH_SHORT).show()
                        } else {
                            Timber.e("************ERROR / ${error.message}")
                        }
                    }
                }
            } else if (token != null) {
                Toast.makeText(context, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                getKakaoUserInfo()
            }
        }

        if (LoginClient.instance.isKakaoTalkLoginAvailable(context)) {
            Timber.i("카카오톡으로")
            LoginClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Timber.i("홈페이지로")
            LoginClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    // 카카오 정보 가져오기
    @SuppressLint("BinaryOperationInTimber")
    fun getKakaoUserInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.e("사용자 정보 요청 실패 / $error.toString()")
            } else if (user != null) {
                Timber.i(
                    "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )

                kakaoUser.name = user.kakaoAccount?.profile?.nickname.toString()
                kakaoUser.oauthKey = user.id.toString()

                // 서버에 요청
                postLogin()
            }
        }
    }

    // 서버 DB 저장 & 토큰 받아오기
    private fun postLogin() {
        val requestLoginData =
            RequestLoginData(oauthKey = kakaoUser.oauthKey, name = kakaoUser.name) // 전송할 데이터

        val call: Call<ResponseLoginData> =
            RufluApp.retrofit.create(UserService::class.java).postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                if (response.isSuccessful) {
                    // token 값 저장
                    SharedPreferenceToken.putSettingItem(
                        getApplication<Application>().applicationContext,
                        "USER_TOKEN",
                        response.body()?.accessToken.toString()
                    )
                    isNew.value = response.body()?.isNewUser
                } else {
                    Timber.i("응답실패 : ${response.code()}, ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Timber.i("error:$t")
            }
        })
    }

    /**
     * 로그아웃
     */
    fun kakaoLogout() {
        UserApiClient.instance.logout { error ->
            if (error != null) Timber.e("로그아웃 실패. SDK 에서 토큰 삭제됨")
            else Timber.e("로그아웃 성공. SDK 에서 토큰 삭제됨")
        }
    }

    /**
     * 카카오 연결 끊기
     */
    fun kakaoDisconnect() {
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Timber.e("연결 끊기 실패")
            } else {
                Timber.i("연결 끊기 성공. SDK 에서 토큰 삭제 됨")
            }
        }
    }
}
