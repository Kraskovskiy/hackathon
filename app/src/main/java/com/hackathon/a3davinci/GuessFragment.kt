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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startTimer()

    }


    fun checkAnswer(answer: String) {
        TODO()
    }

    private fun startTimer() {
        var tm: Long = 30
        kotlin.concurrent.timer("timer", true, 0, 1000, {
            tm--
            timer?.text = tm.toString()
        })
    }
}