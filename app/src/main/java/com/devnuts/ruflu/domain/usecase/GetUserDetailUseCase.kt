package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.domain.entities.UserDetailEntity
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.domain.repository.SomeRepository
import javax.inject.Inject


class GetUserDetailUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(userId: String): Result<UserDetailEntity> {
        return homeRepository.getUserDetailInfo(userId)
    }
}