package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackathon.a3davinci.firebase.DaFirebase

class DrawFragment : Fragment() {


    fun newInstance(gameId: String, userId: String) : DrawFragment {
        val args : Bundle = Bundle()
        val fragment = DrawFragment()
        args.putString(GuessFragment.ARG_GUESS_GAME_ID, gameId)
        args.putString(GuessFragment.ARG_GUESS_USER_ID, userId)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_draw, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text: TextView = view.findViewById(R.id.word)
        val firebase = DaFirebase()
        firebase.wordsRef.addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mapWord: ArrayList<String> = snapshot.value as ArrayList<String>
                val index = (Math.random() * mapWord.size).toInt()
                text.text = mapWord.get(index)

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}