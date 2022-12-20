package com.devnuts.ruflu.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.ui.adapter.MsgListAdapter
import com.devnuts.ruflu.ui.model.ChatMessage
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.databinding.MyChatRoomBinding
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException


class ChatRoomSubSEFrag : Fragment() {

    private lateinit var bind : MyChatRoomBinding
    private lateinit var mSocket : Socket
    private lateinit var roomNo : String
    private lateinit var toUserName: String
    private lateinit var toUserImgUrl : String
    private lateinit var recyclerView: RecyclerView
    private lateinit var msgEdit: EditText
    private lateinit var adapter: MsgListAdapter
    private lateinit var submitBtn: Button

    private val parentViewModel: ChatSharedViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    private fun init() {
        try {
            mSocket = IO.socket(RufluApp.url + ":" + RufluApp.port)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        mSocket.on("new message",onMessage)
        mSocket.connect()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = MyChatRoomBinding.inflate(inflater, container, false)
        recyclerView = bind.msgRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        //adapter = MsgListAdapter()
        recyclerView.adapter = adapter
        msgEdit = bind.msgEdit
        submitBtn = bind.submitBtn
        roomNo = parentViewModel.chatRoom.value!!.roomNo
        mSocket.emit("join", roomNo)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
    }

    private fun initEventListener() {
        submitBtn.setOnClickListener {
            val msg = msgEdit.text.toString()
            val data : HashMap<String, String> = HashMap()
            val userId = RufluApp.sharedPreference.getSettingString("user_id")
            data.put("message", msg)
            data.put("roomNo", roomNo)

            mSocket.emit("new message", data)
            adapter.addItem(ChatMessage(userId!!, msg, ""))

            // 초기화
            msgEdit.setText("")
        }
    }

    var onMessage = Emitter.Listener { args ->
        val obj = JSONObject(args[0].toString())

        val data = ChatMessage(toUserName, obj.getString("message"), toUserImgUrl)
        Log.d("ChatRoom.onMessage", obj.toString())
        requireActivity().runOnUiThread(object : Runnable{
            override fun run() {
                (Runnable {
                    kotlin.run {
                        adapter.addItem(data)
                    }
                })
            }
        })
    }
}