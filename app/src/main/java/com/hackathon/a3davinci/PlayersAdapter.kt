package com.hackathon.a3davinci

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hackathon.a3davinci.model.User

class PlayersAdapter(val context : Context? ) : RecyclerView.Adapter<ViewHolder>() {

    var items: MutableList<User> = mutableListOf()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.playerNameTextView.text = items[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false))
    }
}


class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val playerNameTextView : TextView = itemView.findViewById(R.id.player_name)
}