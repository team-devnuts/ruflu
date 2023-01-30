package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.UserDetailEntity

data class UserDetailModel(
    val title: String,
    val value: String
)

// Model(Data) -> Entity(Domain Layer)
fun UserDetailModel.toEntity() = UserDetailEntity(
    title = title,
    value = value
)