package com.hackathon.a3davinci

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackathon.a3davinci.firebase.DaFirebase
import com.hackathon.a3davinci.model.ResponseUser
import com.hackathon.a3davinci.model.User
import java.util.Collections.addAll

class LobbyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_lobby, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.waiting_room_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerView.adapter = PlayersAdapter(context)

        val firebase = DaFirebase()
        firebase.gamesRef.child("-LEfeuUfa_xyfd_jvqP-").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mapGame = snapshot.value as HashMap<String, Any>
                Log.i("DATA FOR RECYCLER VIEW", "$mapGame")
                val players: MutableList<User> = mapGame.get("players") as MutableList<User>
                Log.i("DATA FOR RECYCLER VIEW Players", "$players")

                val adapter = recyclerView.adapter as PlayersAdapter
                adapter.items = adapter.items.toMutableList().apply {
                    addAll(players)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })


        return view
    }
}