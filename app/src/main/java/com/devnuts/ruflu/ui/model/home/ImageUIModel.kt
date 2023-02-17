package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class ImageUIModel(
    override val type: CellType = CellType.IMAGE_CEL,
    val image: String
): Model(type)