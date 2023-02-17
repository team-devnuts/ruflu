package com.devnuts.ruflu.domain.entities

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.DetailUIModel
import com.devnuts.ruflu.ui.model.home.ImageUIModel

data class DetailEntity(
    val title: String,
    val value: String
)


fun DetailEntity.toUiModel() = DetailUIModel(
    type = CellType.DETAIL_CEL,
    title = title,
    value = value
)