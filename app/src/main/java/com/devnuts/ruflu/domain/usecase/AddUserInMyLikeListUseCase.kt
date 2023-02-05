package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.domain.repository.HomeRepository
import javax.inject.Inject

class AddUserInMyLikeListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(userId: HashMap<String, String>): Result<NetworkResponse> {
        return homeRepository.addUserInMyLikeList(userId)
    }
}