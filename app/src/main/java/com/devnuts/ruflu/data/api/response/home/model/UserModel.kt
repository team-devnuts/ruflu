package com.devnuts.ruflu.data.api.response.home.model

import com.devnuts.ruflu.RufluApp
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.UserUtil.getDistanceToUser
import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("nick_name") val nickName: String,
    val birth: String,
    val images: List<ImageModel>,
    val latitude: Double,
    val longitude: Double,
)

// Model(Data) -> Entity(Domain Layer)
fun UserModel.toEntity() = UserEntity(
    userId = userId,
    nickName = nickName,
    age = getAge(birth),
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

fun getAge(birth: String): String {
    return UserUtil.getAge(birth).toString()
}

/*
37.48130107524739, 경도는 126.88499958281852
 */