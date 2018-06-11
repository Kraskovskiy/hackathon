package com.hackathon.a3davinci

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.gms.common.util.CollectionUtils.mutableListOf

class LobbyFragment : Fragment() {
    companion object {
        const val ARG_LOBBY_GAME_ID = "arg_lobby_game_id"
    }
    fun newInstance(gameId : String): LobbyFragment {
        val args: Bundle = Bundle()
        val fragment = LobbyFragment()
        args.putString(ARG_LOBBY_GAME_ID, gameId)
        fragment.arguments = args
        return fragment
    }
    private var recyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_matchmaking, container, false)

        recyclerView = view.findViewById(R.id.waiting_room_recycler)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val asd : MutableList<String> = mutableListOf("fsdf","sdfdf","sdsdg","sggdsg")
        recyclerView?.adapter = PlayersAdapter(asd, context)

        return view
    }
}