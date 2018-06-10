package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DrawFragment : Fragment() {

    var buttonHold: Button? = null
    val sensorFragment = SensorFragment() // TODO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonHold = view.findViewById(R.id.button_hold)
        buttonHold?.let { button ->
            button.setOnTouchListener(DrawTouchListener(sensorFragment))
        }
    }


}

class DrawTouchListener(val sensorFragment: SensorFragment) : View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
            when (event.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    sensorFragment.isActive = true
                }
                MotionEvent.ACTION_UP -> {
                    sensorFragment.isActive = false
                }
            }
        }
        return true
    }

}