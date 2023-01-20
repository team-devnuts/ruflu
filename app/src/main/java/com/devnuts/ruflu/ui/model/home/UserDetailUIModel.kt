package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

class UserDetailUIModel(
    override val type: CellType,
    val name: String,
    val value: String
): Model(type) {
}