package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      return inflater.inflate(R.layout.fragment_main, container, false)
    }

    var buttonNew : Button? = null
    var buttonJoin : Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonNew : Button = view.findViewById(R.id.button_new)
        buttonNew?.setOnClickListener({ _ ->
            activity?.supportFragmentManager?.beginTransaction()?. add(R.id.container, MatchmakingFragment
                        .newInstance(true))?.commit()  })
        val buttonJoin = view.findViewById<Button>(R.id.button_join)
        buttonJoin.setOnClickListener({_ ->
            activity?.supportFragmentManager?.beginTransaction()?.
                add(R.id.container, MatchmakingFragment
                        .newInstance(false))?.commit()  })
    }



}