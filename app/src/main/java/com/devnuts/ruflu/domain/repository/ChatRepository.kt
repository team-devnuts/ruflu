package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import retrofit2.Call

class ChatRepository {
    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getMyChattingRoomList(): Call<List<ChatRoom>> {
        return api.getMyChattingRoomList()
    }
}
