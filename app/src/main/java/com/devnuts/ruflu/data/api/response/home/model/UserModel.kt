package com.devnuts.ruflu.data.api.response.home.model

import android.util.Log
import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.CustomSharedPreference
import com.devnuts.ruflu.util.UserUtil.getDistanceToUser
import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("nick_name") val nickName: String,
    val birth: String,
    val images: List<UserImageModel>,
    val latitude: Double,
    val longitude: Double,
)

// Model(Data) -> Entity(Domain Layer)
fun UserModel.toEntity() = UserEntity(
    userId = userId,
    nickName = nickName,
    age = birth, // 나이 계산 함수 호출
    images = images.map { it.toEntity() },
    distance = getDistanceToUser(
        getLatitude(),
        getLongitude(),
        latitude,
        longitude
    )
)

fun getLatitude(): Double {
    return RufluApp.sharedPreference.getSettingDouble("latitude")

}

fun getLongitude(): Double {
    return RufluApp.sharedPreference.getSettingDouble("longitude")
}


/*
37.48130107524739, 경도는 126.88499958281852
 */