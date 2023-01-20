package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class UserImageUIModel(
    override val type: CellType = CellType.USER_CARD_IMAGE_CEL,
    val image: String
): Model(type)