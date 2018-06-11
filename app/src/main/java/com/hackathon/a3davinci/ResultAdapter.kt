package com.hackathon.a3davinci

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ResultAdapter(val context : Context? ) : RecyclerView.Adapter<ResultViewHolder>() {

    var items: MutableList<HashMap<String, Any>> = mutableListOf()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder?.resultPlayerName.text = items[position].get("name") as String
        holder?.resultScore.text = items[position].get(TODO())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(LayoutInflater.from(context).inflate(R.layout.result_recycler_view_item, parent, false))
    }
}


class ResultViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val resultPlayerName : TextView = itemView.findViewById(R.id.result_name)
    val resultScore : TextView = itemView.findViewById(R.id.result_score)
}