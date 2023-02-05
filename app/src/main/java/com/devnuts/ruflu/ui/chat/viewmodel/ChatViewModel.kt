package com.devnuts.ruflu.ui.chat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.ChatRepository
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import kotlin.collections.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()
    private val _chatRoomList by lazy {
        MutableLiveData<ArrayList<ChatRoom>>().also {
            //getMyChattingRoomList()
        }
    }
    val chatRoomList: MutableLiveData<ArrayList<ChatRoom>> get() = _chatRoomList

//    private fun getMyChattingRoomList() {
//        val call = repository.getMyChattingRoomList()
//
//        call.enqueue(object : Callback<List<ChatRoom>> {
//            override fun onResponse(
//                call: Call<List<ChatRoom>>,
//                response: Response<List<ChatRoom>>
//            ) {
//                if (response.isSuccessful) {
//                    Timber.d("Success Req []")
//                    val roomList: List<ChatRoom>? = response.body()
//                    _chatRoomList.value =
//                        if (roomList != null) roomList as ArrayList<ChatRoom> else ArrayList()
//                }
//            }
//
//            override fun onFailure(call: Call<List<ChatRoom>>, t: Throwable) {
//                Timber.tag(":: callback fail ::").e(t)
//            }
//        })
//    }

    val getChatRoomByPos = { pos: Int -> _chatRoomList.value?.get(pos) }
}
