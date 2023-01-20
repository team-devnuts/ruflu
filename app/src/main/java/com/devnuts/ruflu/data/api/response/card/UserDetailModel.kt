package com.devnuts.ruflu.data.api.response.card

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel

data class UserDetailModel(
    val name: String,
    val value: String
)

fun UserDetailModel.toUIModel() = UserDetailUIModel(
    type = CellType.USER_DETAIL_CEL,
    name = this.name,
    value = this.value
)