package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class GuessFragment : Fragment() {

    private var guessField: TextView? = null
    private var makeGuessButton: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_guess, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val guessField = TextView(this.context)
        val makeGuessButton: Button = view.findViewById(R.id.button_new)
        makeGuessButton.setOnClickListener({ _ ->
            makeGuessButton.isEnabled = false
            var playerGuess = guessField.text
        })

    }

}