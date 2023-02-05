package com.devnuts.ruflu.ui.chat.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.databinding.ItemMyChatRoomBinding
import com.devnuts.ruflu.ui.adapter.MassagesAdapter
import com.devnuts.ruflu.ui.chat.viewmodel.ChatSharedViewModel
import com.devnuts.ruflu.ui.model.chat.ChatMessage
import com.devnuts.ruflu.RufluApp
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.net.URISyntaxException
import org.json.JSONObject
import timber.log.Timber

class ChatRoomFragment : Fragment() {
    private lateinit var bind: ItemMyChatRoomBinding
    private lateinit var mSocket: Socket
    private lateinit var roomNo: String
    private lateinit var toUserName: String
    private lateinit var toUserImgUrl: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var msgEdit: EditText
    private lateinit var adapter: MassagesAdapter
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
            //mSocket = IO.socket(RufluApp.url + ":" + RufluApp.port)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        mSocket.on("new message", onMessage)
        mSocket.connect()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = ItemMyChatRoomBinding.inflate(inflater, container, false)
        recyclerView = bind.msgRecycler
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        // adapter = MsgListAdapter()
        recyclerView.adapter = adapter
        msgEdit = bind.msgEdit
        submitBtn = bind.submitBtn
        roomNo = parentViewModel.chatRoom.value!!.roomNo
        mSocket.emit("join", roomNo)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initEventListener() {
        submitBtn.setOnClickListener {
            val data: HashMap<String, String> = HashMap()
            val msg = msgEdit.text.toString()
            val userId = RufluApp.sharedPreference.getSettingString("user_id")
            data[MESSAGE] = msg
            data[ROOM_NO] = roomNo

            mSocket.emit("new message", data)
            adapter.addItem(ChatMessage(userId!!, msg, ""))

            // 초기화
            msgEdit.setText("")
        }
    }

    private var onMessage = Emitter.Listener { args ->
        val obj = JSONObject(args[0].toString())
        val data = ChatMessage(toUserName, obj.getString("message"), toUserImgUrl)
        Timber.tag("ChatRoom.onMessage").d(obj.toString())

        requireActivity().runOnUiThread {
            (Runnable {
                kotlin.run {
                    adapter.addItem(data)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
    }

    companion object {
        private const val MESSAGE = "message"
        private const val ROOM_NO = "roomNo"
    }
}
