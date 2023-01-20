package com.devnuts.ruflu.ui.model.onboarding

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class FancyUIModel(
    override val type: CellType = CellType.FANCY_CEL,
    val fancy: String,
    var isSelected: Boolean
) : Model(type)