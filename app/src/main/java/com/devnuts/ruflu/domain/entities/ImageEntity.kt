package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.ImageUIModel

data class ImageEntity (
    val image: String
)


// Entity(Domain) -> Model(Data Layer)



// Entity(Domain) -> UiModel(Presenter Layer)
fun ImageEntity.toUiModel() = ImageUIModel(
    type = CellType.IMAGE_CEL,
    image = image
)

