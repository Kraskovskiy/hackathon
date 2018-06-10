package com.hackathon.a3davinci.model

import android.util.Log

data class ResponseUser(val response: HashMap<String, Any>) {
    fun toUser(): User {
        val user = User()
        for(item in response.entries){
            if (item.key == "score") {
                Log.i("ITEM", item.toString())
                user.score = item.value as Long
            } else if (item.key == "name") {
                user.name = item.value as String
            } else {
                user.uuid = item.value as String
            }

        }
        return user
    }
}