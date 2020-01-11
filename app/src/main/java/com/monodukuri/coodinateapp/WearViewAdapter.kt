package com.monodukuri.coodinateapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WearViewAdapter(private val context: Context, private val memoList: List<Wear>) :
    RecyclerView.Adapter<WearViewAdapter.WearViewHolder>() {

    class WearViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userIconImageView: ImageView = view.findViewById(R.id.userIcon)
        val userNameTextView: TextView = view.findViewById(R.id.userName)
        val dateTextView: TextView = view.findViewById(R.id.date)
        val contentTextView: TextView = view.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WearViewHolder =
        WearViewHolder(LayoutInflater.from(context).inflate(R.layout.list_wear, parent, false))

    override fun getItemCount(): Int = memoList.size

    override fun onBindViewHolder(holder: WearViewHolder, position: Int) {
        holder.userIconImageView.setImageResource(R.mipmap.ic_launcher)
        holder.userNameTextView.text = "fu_neko"
        holder.dateTextView.text = memoList[position].date
        holder.contentTextView.text = memoList[position].content
    }
}