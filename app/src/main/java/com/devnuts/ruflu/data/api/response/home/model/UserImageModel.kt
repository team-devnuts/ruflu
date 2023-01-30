package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.UserImageEntity

data class UserImageModel(
    val image: String
)

// Model(Data) -> Entity(Domain Layer)
fun UserImageModel.toEntity() = UserImageEntity(
    image = image
)