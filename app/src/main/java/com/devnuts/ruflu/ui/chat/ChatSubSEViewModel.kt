package com.devnuts.ruflu.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import com.devnuts.ruflu.domain.repository.ChatRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class ChatSubSEViewModel : ViewModel() {

    private val repository = ChatRepository()
    private val _chatRoomList by lazy {
        MutableLiveData<ArrayList<ChatRoom>>().also {
            init()
        }
    }
    val chatRoomList: MutableLiveData<ArrayList<ChatRoom>> get() = _chatRoomList

    private fun init() {
        getMyChattingRoomList()
    }

    fun getMyChattingRoomList() {
        val call = repository.getMyChattingRoomList()

        call.enqueue(object : Callback<List<ChatRoom>>{
            override fun onResponse(call: Call<List<ChatRoom>>, response: Response<List<ChatRoom>>) {
                if(response.isSuccessful) {
                    Log.d("ChatRoomList API", "Success Req []")
                    val roomList: List<ChatRoom>? = response.body()
                    _chatRoomList.value = if(roomList != null) roomList as ArrayList<ChatRoom> else ArrayList()
                }
            }

            override fun onFailure(call: Call<List<ChatRoom>>, t: Throwable) {
                Log.d("ChatRoomList API", ":: callback fail ::")
                Log.d("ChatRoomList API", "Error Message" + t.message)
            }

        })
    }

    val getChatRoomByPos = { pos : Int -> _chatRoomList.value?.get(pos)}


}