package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.ImageEntity

data class ImageModel(
    val image: String
)

// Model(Data) -> Entity(Domain Layer)
fun ImageModel.toEntity() = ImageEntity(
    image = image
)