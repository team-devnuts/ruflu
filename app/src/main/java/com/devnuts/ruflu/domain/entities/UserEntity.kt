package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.data.api.response.home.model.UserDetailModel
import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.data.api.response.home.model.toEntity
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel

data class UserEntity(
    val userId: String,
    val nickName: String,
    val age: String,
    val images: List<UserImageEntity>,
    val distance: Double = 0.0
)

// Entity(Domain) -> Model(Data Layer)
fun UserEntity.toModel() {

}

// Entity(Domain) -> UI Model(Presenter Layer)
fun UserEntity.toUiModel(
    cellType: CellType = CellType.USER_CARD_CEL
) = UserUIModel(
    userId = userId,
    nickName = nickName,
    age = age,
    images = images.map { it.toUiModel() },
    distance = distance
)