package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class DetailUIModel(
    override val type: CellType,
    val title: String,
    val value: String
): Model(type)