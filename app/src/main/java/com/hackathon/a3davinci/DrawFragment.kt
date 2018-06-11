package com.hackathon.a3davinci

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hackathon.a3davinci.firebase.DaFirebase
import android.widget.Button

class DrawFragment : Fragment() {

    var drawer: PictureDrawer? = null
    fun newInstance(gameId: String, userId: String) : DrawFragment {
        val args : Bundle = Bundle()
        val fragment = DrawFragment()
        args.putString(GuessFragment.ARG_GUESS_GAME_ID, gameId)
        args.putString(GuessFragment.ARG_GUESS_USER_ID, userId)
        fragment.arguments = args
        return fragment
    }

    var buttonHold: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_draw, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sensorFragment = SensorFragment(this.context!!) // TODO
        buttonHold = view.findViewById(R.id.button_hold)
        drawer = view.findViewById(R.id.surf)
        buttonHold?.let { button ->
            button.setOnTouchListener(DrawTouchListener(sensorFragment, this.context!!))
        }
        val text: TextView = view.findViewById(R.id.word)
        val firebase = DaFirebase()
        firebase.wordsRef.addListenerForSingleValueEvent(object : ValueEventListener {
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
        inner class DrawTouchListener(val sensorFragment: SensorFragment, val context: Context) : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            event?.let {
                when (event.getAction()) {
                    MotionEvent.ACTION_DOWN -> {
                        sensorFragment.isActive = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        sensorFragment.isActive = true
                    }
                    MotionEvent.ACTION_UP -> {
                        sensorFragment.isActive = false
                        this@DrawFragment.drawer?.setData(sensorFragment.points)
//                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, GuessFragment().newInstance(arguments?.getString(GuessFragment.ARG_GUESS_GAME_ID)!!, arguments?.getString(GuessFragment.ARG_GUESS_USER_ID)!!))?.
//                                addToBackStack(null)?.commit()
                    }
                    else -> {}
                }
            }
            return true
        }
    }
}
