package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.DetailEntity

data class DetailModel(
    val title: String,
    val value: String
)

fun DetailModel.toEntity() = DetailEntity(
    title = title,
    value = value
)