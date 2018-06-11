package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class GuessFragment : Fragment() {
    companion object {
        const val ARG_GUESS_GAME_ID = "arg_guess_game_id"
        const val ARG_GUESS_USER_ID = "arg_guess_user_id"
    }

    fun newInstance(gameId: String, userId: String) : GuessFragment {
        val args : Bundle = Bundle()
        val fragment = GuessFragment()
        args.putString(ARG_GUESS_GAME_ID, gameId)
        args.putString(ARG_GUESS_USER_ID, userId)
        fragment.arguments = args
        return fragment
    }

    private var canonManTextView: TextView? = null
    private var timer: TextView? = null
    private var image: ImageView? = null
    private var answerEditText: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_guess, container, false)
        canonManTextView = view.findViewById(R.id.canon_man)
        timer = view.findViewById(R.id.timer)
        image = view.findViewById(R.id.image)
        answerEditText = view.findViewById(R.id.answer)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTimer(arguments?.getString(ARG_GUESS_GAME_ID), arguments?.getString(ARG_GUESS_USER_ID))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    fun checkAnswer(answer: String) {
        TODO()
    }

    private fun startTimer(gameId : String?, userId : String?) {
        var tm: Long = 30
        kotlin.concurrent.timer("timer", true, 0, 1000, {
            tm--
            timer?.text = tm.toString()
            if(tm == 0.toLong()) { goToResult(gameId, userId)}
        })
    }

    private fun goToResult(gameId : String?, userId : String?) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ResultFragment().newInstance(gameId, userId))?.
                addToBackStack(null)?.commit()
    }
}


