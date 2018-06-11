package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackathon.a3davinci.firebase.DaFirebase


class LobbyFragment : Fragment() {
    companion object {
        const val ARG_LOBBY_GAME_ID = "arg_lobby_game_id"
        const val ARG_USER = "arg_user"
    }

    fun newInstance(gameId : String, userId: String) : LobbyFragment {
        var args = Bundle()
        val fragment = LobbyFragment()
        args.putString(ARG_LOBBY_GAME_ID, gameId)
        args.putString(ARG_USER, userId)
        fragment.arguments = args
        return fragment

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_lobby, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.waiting_room_recycler)
        val gameIdEditText: EditText = view.findViewById(R.id.game_id_editText)
        val startButton: Button = view.findViewById(R.id.start_game_button)
        startButton.setOnClickListener({
            val firebase = DaFirebase()
            firebase.gamesRef.child(this.arguments!!.getString(ARG_LOBBY_GAME_ID)).child("started").setValue(true)
        })
        gameIdEditText.setText(this.arguments!!.getString(ARG_LOBBY_GAME_ID))
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerView.adapter = PlayersAdapter(context)
        var self = this

        val firebase = DaFirebase()
        firebase.gamesRef.child(this.arguments!!.getString(ARG_LOBBY_GAME_ID)).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mapGame = snapshot.value as HashMap<String, Any>
                Log.i("DATA FOR RECYCLER VIEW", "$mapGame")
                val players: List<HashMap<String, Any>> = mapGame.get("players") as List<HashMap<String, Any>>
                val adapter = recyclerView.adapter as PlayersAdapter
                adapter.items = adapter.items.toMutableList().apply {
                    removeAll(adapter.items)
                    addAll(players)
                }
                adapter.notifyDataSetChanged()
                if (mapGame.get("started") == true) {
                    for (i in players) {
                        Log.i("INSIDE", "$i")
                        if(self.arguments!!.getString(ARG_USER) == i.get("uuid") && i.get("drawer") == true) {
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, DrawFragment().newInstance(self.arguments!!.getString(ARG_LOBBY_GAME_ID), self.arguments!!.getString(ARG_USER))
                            )?.addToBackStack(null)?.commit()
                        } else if(self.arguments!!.getString(ARG_USER) == i.get("uuid") && i.get("drawer") == false) {
                            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, GuessFragment().newInstance(self.arguments!!.getString(ARG_LOBBY_GAME_ID),self.arguments!!.getString(ARG_USER))
                            )?.addToBackStack(null)?.commit()
                        }

                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })


        return view
    }
}