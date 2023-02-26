package com.devnuts.ruflu.domain.usecase

import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.repository.SomeRepository
import javax.inject.Inject

class GetUserMatchedWithMeListUseCase @Inject constructor (
    private val someRepository: SomeRepository
) {

    suspend operator fun invoke(): Result<List<UserEntity>> {
        return someRepository.getUserMatchedWithMeList()
    }
}