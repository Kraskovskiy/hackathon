package com.hackathon.a3davinci.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.hackathon.a3davinci.model.Game
import com.hackathon.a3davinci.model.User
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.hackathon.a3davinci.model.ResponseGame
import com.hackathon.a3davinci.model.ResponseUser







class DaFirebase {

    var database = FirebaseDatabase.getInstance()
    var usersRef = database.getReference("users")
    var gamesRef = database.getReference("games")
    var wordsRef = database.getReference("words")


    fun createUser(user: User) {
            val key = usersRef.push().key
            user.uuid = key!!
            usersRef.child(key).setValue(user)
    }

    fun createGame(game: Game) {
        val key = gamesRef.push().key
        game.uuid = key!!
        gamesRef.child(key).setValue(game)
    }

    fun addPlayer(gameId: String, userId: String) {
        var user = User()
        var game = Game()
        var mapGame = hashMapOf<String, Any>()
        Log.e(  "REFERECE",      usersRef.child(userId).child("name").toString())
        usersRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val resUser = ResponseUser(snapshot.value as HashMap<String, Any>)
                user = resUser.toUser()
                Log.e("user", "${user}")
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        gamesRef.child(gameId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val resGame = ResponseGame(snapshot.value as HashMap<String, Any>)
                game = resGame.toGame()
                Log.e("SNAPSHOT",snapshot.toString())
                mapGame = snapshot.value as HashMap<String, Any>
                Log.e("MAP1", mapGame.toString())

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

//        val phoneQuery = gamesRef.equalTo(gameId)
//        phoneQuery.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (singleSnapshot in dataSnapshot.children) {
//                    game = singleSnapshot.getValue(Game::class.java)!!
//                    Log.e("GAME", game.toString())
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Log.e("cancel", "onCancelled", databaseError.toException())
//            }
//        })

        Log.e("MAP2", mapGame.toString())
        val listOfPlayers: MutableList<User> = mapGame.get("players") as MutableList<User>
        listOfPlayers.add(user)
        mapGame.set("players", listOfPlayers)
        gamesRef.updateChildren(mapGame)
    }
}


