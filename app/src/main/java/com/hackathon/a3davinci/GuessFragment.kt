package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import rjsv.circularview.CircleView

class GuessFragment : Fragment() {
    private var canonManTextView : TextView? = null
    private var timer : CircleView? = null
    private var image : ImageView? = null
    private var answerEditText : EditText? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_guess, container, false)
        canonManTextView = view.findViewById(R.id.canon_man)
        timer = view.findViewById(R.id.timer)
        image = view.findViewById(R.id.image)
        answerEditText = view.findViewById(R.id.answer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }



    fun checkAnswer(answer : String) {
        TODO()
    }
}