package com.devnuts.ruflu.ui.model.onboarding

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class AcademyUIModel(
    override val type: CellType = CellType.ACADEMY_CEL,
    val academy: String,
    var isSelected: Boolean
) : Model(type)