package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel

data class UserEntity(
    val userId: String,
    val nickName: String,
    val age: String,
    val images: List<ImageEntity>,
    val distance: Float = 0F
)

// Entity(Domain) -> Model(Data Layer)
fun UserEntity.toModel() {

}

// Entity(Domain) -> UI Model(Presenter Layer)
fun UserEntity.toUiModel(
    cellType: CellType
) = UserUIModel(
    type = cellType,
    userId = userId,
    nickName = nickName,
    age = age,
    images = images.map { it.toUiModel() },
    distance = distance
)