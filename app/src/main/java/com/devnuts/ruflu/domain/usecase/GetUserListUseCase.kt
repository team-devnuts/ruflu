package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.repository.HomeRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(): Result<List<UserEntity>> {
        return homeRepository.getUserList()
    }
}