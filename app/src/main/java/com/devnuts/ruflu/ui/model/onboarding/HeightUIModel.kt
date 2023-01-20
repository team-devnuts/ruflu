package com.devnuts.ruflu.ui.model.onboarding

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class HeightUIModel(
    override val type: CellType = CellType.HEIGHT_CEL,
    val height: Int,
    var isSelected: Boolean
) : Model(type)