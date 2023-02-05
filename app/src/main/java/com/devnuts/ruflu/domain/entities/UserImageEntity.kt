package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserImageUIModel

data class UserImageEntity (
    val image: String
)


// Entity(Domain) -> Model(Data Layer)
fun UserImageEntity.toModel() {

}

// Entity(Domain) -> UiModel(Presenter Layer)
fun UserImageEntity.toUiModel() = UserImageUIModel(
    type = CellType.USER_CARD_IMAGE_CEL,
    image = image
)

