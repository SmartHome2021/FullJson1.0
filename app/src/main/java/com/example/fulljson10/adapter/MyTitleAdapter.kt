package com.example.fulljson10.adapter

import Actor
import Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fulljson10.R



class MyTitleAdapter(

    private val context: Context,
    private val movieList: List<Item>)

    : RecyclerView.Adapter<MyTitleAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val screenshot: ImageView = itemView.findViewById(R.id.TitleScreenshot)

        fun bind(listItem: Item) {
            Glide.with(screenshot.context).load(listItem.image).into(screenshot)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
    }
}