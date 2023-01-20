package com.devnuts.ruflu.ui.model.home

import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model

data class UserUIModel(
    override val type: CellType = CellType.USER_CARD_CEL,
    val userId: String,
    val nickName: String,
    val birth: String,
    //val detailInfo: List<Model>,
    val images: List<Model>,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    var distance: Double = 0.0
): Model(type)

/*
    val gender: String = "",
    val wei: String = "",
    val hei: String = "",
    val occu: String = "",
    val occu_dtl: String = "",
    val rign: String = "",
    val alch: String = "",
    val smk_yn: String = "",
    val mbti: String = "",
    val fancy: String = "",
    val intd: String = "",
    val qa1: String = "",
    val qa2: String = "",
    val hob: String = "",

 */