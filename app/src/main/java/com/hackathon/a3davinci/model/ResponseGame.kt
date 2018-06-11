package com.hackathon.a3davinci.model


data class ResponseGame(val response: HashMap<String, Any>) {
    fun toGame(): Game {
        val game = Game()
        for(item in response.entries){
            if (item.key == "players") {
                game.players = item.value as MutableList<User>
            } else if (item.key == "uuid") {
                game.uuid = item.value as String
            }
        }
        return game
    }
}