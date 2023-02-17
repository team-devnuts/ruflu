package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel

data class UserDetailEntity(
    val userId: String,
    val nickName: String,
    val birth: String,
    val images: List<ImageEntity>,
    val detailInfo: List<DetailEntity>,
    val distance: Float
)


fun UserDetailEntity.toUiModel(
    cellType: CellType
) = UserDetailUIModel(
    type = cellType,
    userId = userId,
    nickName = nickName,
    birth = birth,
    images = images.map { it.toUiModel() },
    detailInfo = detailInfo.map { it.toUiModel() },
    distance = distance
)