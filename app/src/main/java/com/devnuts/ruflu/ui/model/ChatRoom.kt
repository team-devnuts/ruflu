package com.devnuts.ruflu.ui.model

data class ChatRoom (
        val userId : String,
        val toUserId : String,
        val roomNo : String,
        val toUserImg: String,
        val toNickName : String,
        val toCurMsg : String
    ){

}