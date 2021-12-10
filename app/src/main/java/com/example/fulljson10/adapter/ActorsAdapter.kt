package com.example.fulljson10.adapter

import Actor
import Item
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fulljson10.R

class ActorsAdapter (val moviList: List<Actor>):
    RecyclerView.Adapter<ActorsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.ActorImage)
        val actorName: TextView = itemView.findViewById(R.id.ActorName)
        val actorRole: TextView = itemView.findViewById(R.id.ActorRole)

        @SuppressLint("SetTextI18n")
        fun bind(listItem: Actor) {
            Glide.with(image.context).load(listItem.image).into(image)
            actorName.text = "Name: ${listItem.name}"
            actorRole.text = "Role: ${listItem.asCharacter}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.actors_full_info, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = moviList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem1 = moviList[position]
        holder.bind(listItem1)
    }
    }
