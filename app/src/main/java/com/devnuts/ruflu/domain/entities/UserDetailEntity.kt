package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel

data class UserDetailEntity(
    val title: String,
    val value: String
)


fun UserDetailEntity.toUiModel() = UserDetailUIModel(
    type = CellType.USER_DETAIL_CEL,
    title = title,
    value = value
)