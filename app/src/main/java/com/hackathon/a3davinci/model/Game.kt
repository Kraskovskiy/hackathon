package com.hackathon.a3davinci.model

data class Game(var players: MutableList<User> = mutableListOf(), var uuid: String = "", var pic: String = "",var started: Boolean = false)

