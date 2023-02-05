package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.domain.repository.SomeRepository
import javax.inject.Inject

class AddUserInMyMatchListUseCase @Inject constructor(
    private val someRepository: SomeRepository
) {
    suspend operator fun invoke(userId: String): Result<NetworkResponse> {
        return someRepository.addUserInMyMatchList(userId)
    }
}