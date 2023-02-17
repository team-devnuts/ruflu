package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.UserDetailEntity
import com.devnuts.ruflu.util.UserUtil
import com.google.gson.annotations.SerializedName

data class UserDetailModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("nick_name") val nickName: String,
    val birth: String,
    val images: List<ImageModel>,
    @SerializedName("detail_info") val detailInfo: List<DetailModel>,
    val latitude: Double,
    val longitude: Double,
)

fun UserDetailModel.toEntity() = UserDetailEntity(
    userId = userId,
    nickName = nickName,
    birth = birth,
    images = images.map { it.toEntity() },
    detailInfo = detailInfo.map { it.toEntity() },
    distance = UserUtil.getDistanceToUser(
        getLatitude(),
        getLongitude(),
        latitude,
        longitude
    )
)