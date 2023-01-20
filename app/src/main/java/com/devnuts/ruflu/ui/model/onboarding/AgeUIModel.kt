package com.devnuts.ruflu.ui.model.onboarding

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class AgeUIModel(
    override val type: CellType = CellType.AGE_CEL,
    val age: Int,
    var isSelected: Boolean
) : Model(type)