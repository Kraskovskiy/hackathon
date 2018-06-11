package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        return super.onCreateView(inflater, container, savedInstanceState)


    }
}