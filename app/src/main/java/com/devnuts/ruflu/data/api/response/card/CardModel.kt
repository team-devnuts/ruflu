package com.devnuts.ruflu.data.api.response.card

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.CardImageUIModel
import com.devnuts.ruflu.ui.model.home.CardUIModel

data class CardModel(
    val user_id: String,
    val nick_nm: String,
    val birth: String,
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
    val images: List<String>
)

fun CardModel.toHomeMenuModel(
    cellType: CellType = CellType.USER_CARD_CEL
): CardUIModel {

    return CardUIModel(
        type = cellType,
        user_id = this.user_id,
        nick_nm = this.nick_nm,
        birth = this.birth,
        images = this.images.map {
            CardImageUIModel(CellType.USER_CARD_IMAGE_CEL, it)
        }
    )
}