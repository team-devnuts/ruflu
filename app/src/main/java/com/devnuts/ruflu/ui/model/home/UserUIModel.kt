package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class UserUIModel(
    override val type: CellType,
    val userId: String,
    val nickName: String,
    val age: String,
    val images: List<Model>,
    var distance: Float
): Model(type)