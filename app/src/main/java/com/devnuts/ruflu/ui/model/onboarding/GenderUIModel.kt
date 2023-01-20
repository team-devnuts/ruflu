package com.devnuts.ruflu.ui.model.onboarding

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class GenderUIModel(
    override val type: CellType = CellType.GENDER_CEL,
    val resourceId: Int,
    val name: String,
) : Model(type)