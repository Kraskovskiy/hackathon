package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ResultFragment : Fragment() {
    companion object {
        const val ARG_RESULT_USER_ID = "arg_result_user_id"
        const val ARG_RESULT_GAME_ID = "arg_result_game_id"
    }

    fun newInstance(gameId : String?, userId : String?) : ResultFragment {
        val args : Bundle = Bundle()
        val fragment = ResultFragment()
        args.putString(ARG_RESULT_GAME_ID, gameId)
        args.putString(ARG_RESULT_USER_ID, userId)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }
}