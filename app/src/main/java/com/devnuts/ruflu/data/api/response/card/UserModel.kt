package com.devnuts.ruflu.data.api.response.card

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("user_id") val user_id: String,
    @SerializedName("nick_nm") val nick_nm: String,
    val birth: String,
    //@SerializedName("detail_info") val detailInfo: List<UserDetailModel>,
    val images: List<String>,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

fun UserModel.toCardUIModel(
    cellType: CellType = CellType.USER_CARD_CEL
): UserUIModel {

    return UserUIModel(
        type = cellType,
        userId = this.user_id,
        nickName = this.nick_nm,
        birth = this.birth,
//        detailInfo = this.detailInfo.map {
//            it.toUIModel()
//        },
        images = this.images.map {
            UserImageUIModel(CellType.USER_CARD_IMAGE_CEL, it)
        }
    )
}

/*
    val gender: String,
    val wei: String,
    val hei: String,
    val occu: String,
    val occu_dtl: String,
    val rign: String,
    val alch: String,
    val smk_yn: String,
    val mbti: String,
    val fancy: String,
    val intd: String,
    val qa1: String,
    val qa2: String,
    val hob: String,
 */