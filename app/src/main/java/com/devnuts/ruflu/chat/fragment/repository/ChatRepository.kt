package com.devnuts.ruflu.chat.fragment.repository

import com.devnuts.ruflu.chat.fragment.model.ChatRoom
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.comm.retrofit.ServerAPI
import retrofit2.Call

class ChatRepository {
    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getMyChattingRoomList() : Call<List<ChatRoom>> {

        return api.getMyChattingRoomList()
    }

}