package com.hackathon.a3davinci

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hackathon.a3davinci.firebase.DaFirebase
import com.hackathon.a3davinci.model.Game
import com.hackathon.a3davinci.model.User

class MatchmakingFragment : Fragment() {

    private var playerCounterTextView: TextView? = null
    private var joinCodeTextView: TextView? = null
    private var enterCodeEditText: EditText? = null
    private var enterNameEditText: EditText? = null
    private var startConnectButton: Button? = null

    companion object {
        const val ARG_MATCHMAKING_ISHOST = "arg_matchmaking_isHost"

        fun newInstance(isHost: Boolean): MatchmakingFragment {
            val args: Bundle = Bundle()
            val fragment = MatchmakingFragment()
            args.putBoolean(ARG_MATCHMAKING_ISHOST, isHost)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_matchmaking, container, false)


        enterCodeEditText = view?.findViewById(R.id.enter_code)
        enterNameEditText = view?.findViewById(R.id.enter_name)
        startConnectButton = view?.findViewById(R.id.button_start_connect_game)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isHost = this.arguments?.getBoolean(ARG_MATCHMAKING_ISHOST)
        initView(isHost)
        startConnectButton?.setOnClickListener({
            val firebase = DaFirebase()
            when(isHost) {
                true -> {
                    val user = User(enterNameEditText?.text.toString(), -1, firebase.generateUserUUID(), true)
                    val game = Game(mutableListOf(user), firebase.generateGameUIUD())
                    firebase.createGame(game)
                    firebase.createUser(user)
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, LobbyFragment().newInstance(game.uuid, user.uuid)
                    )?.addToBackStack(null)?.commit()
                }

                false -> {
                    val user = User(enterNameEditText?.text.toString(), -1, firebase.generateUserUUID())
                    firebase.createUser(user)
                    firebase.addPlayer(enterCodeEditText?.text.toString(), user.uuid)
                    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, LobbyFragment().newInstance(enterCodeEditText?.text.toString(), user.uuid)
                    )?.addToBackStack(null)?.commit()
                }
            }

        })
    }

    private fun initView(isHost: Boolean?) {
        when (isHost) {
            true -> {
                enterCodeEditText?.visibility = View.GONE
                joinCodeTextView?.visibility = View.VISIBLE
                startConnectButton?.text = getText(R.string.start_game)
            }

            false -> {
                enterCodeEditText?.visibility = View.VISIBLE
                joinCodeTextView?.visibility = View.GONE
                startConnectButton?.text = getText(R.string.connect)
            }
        }

    }

}