package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.UserDetailEntity
import com.google.gson.annotations.SerializedName

data class UserDetailModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("nick_name") val nickName: String,
    val birth: String,
    val images: List<UserImageModel>,
    @SerializedName("detail_info") val detailInfo: List<DetailModel>,
    val latitude: Double,
    val longitude: Double,
) {

}

data class DetailModel(
    val title: String,
    val value: String
)


// Model(Data) -> Entity(Domain Layer)
fun DetailModel.toEntity() = UserDetailEntity(
    title = title,
    value = value
)