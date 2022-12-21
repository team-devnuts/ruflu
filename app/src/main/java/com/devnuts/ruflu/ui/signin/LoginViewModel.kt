package com.devnuts.ruflu.ui.signin

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.login.loginAPI.model.RequestLoginData
import com.devnuts.ruflu.login.loginAPI.model.ResponseLoginData
import com.devnuts.ruflu.login.loginAPI.model.KakaoUser
import com.devnuts.ruflu.login.loginAPI.repository.UserService
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


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var kakaoUser = KakaoUser("","")
    val isNew = MutableLiveData<Boolean>()


    fun checkExistenceToken(context:Context) {
        // 단, hasToken()의 결과가 true 라도 현재 사용자가 로그인 상태임을 보장하지 않습니다.
        if (AuthApiClient.instance.hasToken()) { // 앱 실행 시 사용자가 앞서 로그인을 통해 발급 받은 토큰이 있는지 확인
            // 서버에 액세스 토큰의 유효성을 확인할 수 있는 API
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                // 현재 유효한 access 토큰이 없음
                //access 토큰이 만료된 것이라면 sdk 내부에서 accessToken 을 갱신한다.
                if (error != null) {
                    if (error is KakaoSdkError) {
                        // access 토큰 갱신까지 실패한 것이기 때문에 refresh 토큰이 유효하지 않음, 로그인 필요
                        Log.e(LOGINVIEWMODEL, "로그인 필요 / $error.toString()", error)
                        loginKakaoUser(context)
                    }
                    else {
                        //기타 에러 (에러 시, 레퍼런스 참고해야할 것)
                        Log.e(LOGINVIEWMODEL, "기타 에러 / $error.toString()", error)
                    }
                }
                else{
                    // 토큰 유효성 체크 성공(필요 시 sdk 내부에서 토큰 갱신됨)
                    Log.e(LOGINVIEWMODEL, "로그인 성공 (필요 시 토큰 갱신) / $tokenInfo.toString()", error)
                    // 토큰 갱신 API 호출 함수 구현 (USER_TOKEN 최신화 작업이 필요)
                    loginKakaoUser(context)

                }
            }
        }
        else {
            // hasToken() API 의 결과가 false 라면 토큰이 없는 상태이므로 사용자가 로그인할 수 있도록 처리
            Log.d(LOGINVIEWMODEL, "로그인 필요")
            loginKakaoUser(context)
        }
    }

    fun loginKakaoUser(context: Context) {
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
                        Toast.makeText(context, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(context, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(context, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(context, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        if (error.toString() == ClientErrorCause.TokenNotFound.toString()) {
                            Log.d("*******TokenNotFound", error.toString())
                        Toast.makeText(context, "기타 에러", Toast.LENGTH_SHORT).show()
                        }else {
                            Log.d("************ERROR", ""+ error.message)
                        }
                    }
                }
            } else if (token != null) {
                Toast.makeText(context, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                getKakaoUserInfo()
            }
        }
        Log.e(LOGINVIEWMODEL, "login 호출?")
        if (LoginClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.e(LOGINVIEWMODEL, "카카오톡으로")
            LoginClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Log.e(LOGINVIEWMODEL, "홈페이지로")
            LoginClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    //카카오 정보 가져오기
    fun getKakaoUserInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "사용자 정보 요청 실패 / $error.toString()", error)
            }
            else if (user != null) {
                Log.i(
                    LOGINVIEWMODEL, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                kakaoUser.name = user.kakaoAccount?.profile?.nickname.toString()
                kakaoUser.oauthKey = user.id.toString()

                //서버에 요청
                postLogin()
            }
        }
    }

    // 서버 DB 저장 & 토큰 받아오기
    fun postLogin() {
        val requestLoginData = RequestLoginData(oauthKey = kakaoUser.oauthKey, name = kakaoUser.name) // 전송할 데이터

        val call: Call<ResponseLoginData> = RufluApp.retrofit.create(UserService::class.java).postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                if(response.isSuccessful) {
                    //token 값 저장
                    Log.d("getAccessToken", "응답성공")
                    Log.d("getAcessToken", response.body().toString())
                    SharedPreferenceToken.putSettingItem(getApplication<Application>().applicationContext,"USER_TOKEN", response.body()?.accessToken.toString())
                    isNew.value = response.body()?.isNewUser

                } else {
                    Log.d(LOGINVIEWMODEL, "응답실패 : ${response.code()}, ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    /**
     * 로그아웃
     */
    fun kakaoLogout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(LOGINVIEWMODEL, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }

    /**
     * 카카오 연결 끊
     */
    fun kakaoDisconnect() {
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "연결 끊기 실패", error)
            } else {
                Log.i(LOGINVIEWMODEL, "연결 끊기 성공. SDK 에서 토큰 삭제 됨")
            }
        }
    }
    companion object {
        val LOGINVIEWMODEL = "LoginViewModel"
    }
}