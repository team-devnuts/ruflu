package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class UserDetailUIModel(
    override val type: CellType,
    val userId: String,
    val nickName: String,
    val birth: String,
    val images: List<Model>,
    val detailInfo: List<Model>,
    val distance: Float
): Model(type)