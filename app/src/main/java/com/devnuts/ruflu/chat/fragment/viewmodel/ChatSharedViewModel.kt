package com.devnuts.ruflu.chat.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.chat.fragment.model.ChatRoom

class ChatSharedViewModel : ViewModel() {

    private var _chatRoom : MutableLiveData<ChatRoom> = MutableLiveData()
    val chatRoom get() = _chatRoom

    fun setMyRoom(myRoom : ChatRoom) {
        _chatRoom.value = myRoom
    }

    fun detachRoom() { _chatRoom.value = null }

}