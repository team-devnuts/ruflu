package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.data.api.response.signin.ResponseAuthCode
import com.devnuts.ruflu.domain.repository.MainRepository
import retrofit2.Response
import javax.inject.Inject

class SendUserAuthPhoneNumberUserCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(phoneNumber: String): Response<ResponseAuthCode> {
        return mainRepository.sendUserPhoneNumber(phoneNumber)
    }
}