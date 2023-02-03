package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class UserUIModel(
    override val type: CellType = CellType.USER_CARD_CEL,
    val userId: String,
    val nickName: String,
    val age: String,
    val images: List<Model>,
    var distance: Double
): Model(type)