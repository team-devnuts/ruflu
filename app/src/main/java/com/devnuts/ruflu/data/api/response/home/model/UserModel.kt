package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.entities.UserImageEntity
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("nick_name") val nickName: String,
    val birth: String,
    @SerializedName("detail_info") val detailInfo: List<UserDetailModel>,
    val images: List<String>,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

// Model(Data) -> Entity(Domain Layer)
fun UserModel.toEntity() = UserEntity(
    userId = userId,
    nickName = nickName,
    age = birth, // 나이 계산 함수 호출
    detailInfo = detailInfo.map { it.toEntity() },
    images = images.map { UserImageModel(it).toEntity() },
    distance = 10.0 // 거리 구하는 함수 호출
)