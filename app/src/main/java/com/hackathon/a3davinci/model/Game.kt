package com.hackathon.a3davinci.model

import android.R.attr.author



data class Game(var players: MutableList<User> = mutableListOf(), var uuid: String = "", var pic: String = "") {
//    fun toMap(): Map<String, Any> {
//        val result = HashMap()
//        result.put("uuid", uid)
//        result.put("author", author)
//        result.put("title", title)
//        result.put("body", body)
//        result.put("starCount", starCount)
//        result.put("stars", stars)
//
//        return result
//    }
}